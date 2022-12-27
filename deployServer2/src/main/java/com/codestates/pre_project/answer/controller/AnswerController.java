package com.codestates.pre_project.answer.controller;

import com.codestates.pre_project.answer.dto.AnswerPatchDto;
import com.codestates.pre_project.answer.dto.AnswerPostDto;
import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.answer.mapper.AnswerMapper;
import com.codestates.pre_project.answer.service.AnswerService;
import com.codestates.pre_project.comment.dto.CommentPatchDto;
import com.codestates.pre_project.comment.dto.CommentPostDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.comment.mapper.CommentMapper;
import com.codestates.pre_project.comment.response.MultiResponseDto;
import com.codestates.pre_project.comment.service.CommentService;
import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
@Validated
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;

    public AnswerController(AnswerService answerService,
                             AnswerMapper answerMapper,
                             MemberService memberService){
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerMapper.answerPostDtoToAnswer(answerPostDto);

        Answer createdAnswer = answerService.createAnswer(answer);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponseDto(createdAnswer), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);
        Answer answer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(answerMapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }


    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponseDto(answer), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                      @Positive @RequestParam int size) {
        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, size);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(answerMapper.answersToAnswerResponseDtos(answers), pageAnswers), HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
