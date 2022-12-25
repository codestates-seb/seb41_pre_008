package com.codestates.pre_project.answer.controller;

import com.codestates.pre_project.answer.dto.AnswerPatchDto;
import com.codestates.pre_project.answer.dto.AnswerPostDto;
import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.answer.mapper.AnswerMapper;
import com.codestates.pre_project.answer.service.AnswerService;
import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.dto.MultiResponseDto;
import com.codestates.pre_project.member.entity.Member;
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
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper mapper;

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerPostDto answerPostDto){
        Answer answer = mapper.postToAnswer(answerPostDto);
        Member member = new Member();
        member.setMemberId(answerPostDto.getMemberId());
        answer.setMember(member);
        Answer created = answerService.createAnswer(answer);
        return new ResponseEntity(mapper.answerToResponse(created), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                       @RequestBody AnswerPatchDto answerPatchDto){
        // mapper로 변환
        Answer answer = mapper.patchToAnswer(answerPatchDto);
        // answerId 설정
        answer.setAnswerId(answerId);

        AnswerResponseDto response = mapper.answerToResponse(answerService.updateAnswer(answer));

        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId){
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity(mapper.answerToResponse(answer), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Answer> pages = answerService.findAnswers(page-1,size);
        List<Answer> answers = pages.getContent();
        return new ResponseEntity(new MultiResponseDto<>(mapper.answersToResponses(answers), pages)
                , HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity deleteAnswer(@PathVariable("member-id") @Positive long answerId){
        answerService.deleteAnswer(answerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
