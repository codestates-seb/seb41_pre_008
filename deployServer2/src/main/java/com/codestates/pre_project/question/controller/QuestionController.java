package com.codestates.pre_project.question.controller;

import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.comment.mapper.CommentMapper;
import com.codestates.pre_project.question.dto.QuestionLikesResponseDto;
import com.codestates.pre_project.question.dto.QuestionPatchDto;
import com.codestates.pre_project.question.dto.QuestionPostDto;
import com.codestates.pre_project.question.dto.QuestionResponseDto;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.mapper.QuestionMapper;
import com.codestates.pre_project.question.response.MultiResponseDto;
import com.codestates.pre_project.question.service.QuestionService;
//import com.codestates.pre_project.tag.service.TagService;
import com.codestates.pre_project.tag.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/questions")
@Validated
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final CommentMapper commentMapper;


    public QuestionController(QuestionService questionService,
                              QuestionMapper questionMapper,
                              CommentMapper commentMapper){
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto questionPostDto) {
        Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(questionPostDto));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question), HttpStatus.CREATED);
    }

    @PostMapping("/up/{question-id}")
    public ResponseEntity upQuestion(@PathVariable("question-id")@Positive long questionId){
        QuestionLikesResponseDto response = questionMapper.
                questionToQuestionLikesResponseDto(questionService.upQuestion(questionId));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/down/{question-id}")
    public ResponseEntity downQuestion(@PathVariable("question-id")@Positive long questionId){
        QuestionLikesResponseDto response = questionMapper.
                questionToQuestionLikesResponseDto(questionService.downQuestion(questionId));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody QuestionPatchDto questionPatchDto) {
        questionPatchDto.setQuestionId(questionId);
        Question question = questionService.updateQuestion(questionMapper.questionPatchDtoToQuestion(questionPatchDto));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question), HttpStatus.OK);
    }


    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId) {
        Question question = questionService.findQuestion(questionId);

        return new ResponseEntity<>(questionMapper.questionToQuestionResponseDto(question), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(questionMapper.questionsToQuestionResponseDtos(questions), pageQuestions), HttpStatus.OK);
    }

    /*
    @GetMapping
    public ResponseEntity getQuestions() {
        List<Question> questions = questionService.findQuestions();

        List<QuestionResponseDto> response =
                questions.stream()
                        .map(question -> questionMapper.questionToQuestionResponseDto(question))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     */

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
