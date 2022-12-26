package com.codestates.pre_project.comment.controller;

import com.codestates.pre_project.comment.dto.CommentPatchDto;
import com.codestates.pre_project.comment.dto.CommentPostDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.comment.mapper.CommentMapper;
import com.codestates.pre_project.comment.response.MultiResponseDto;
import com.codestates.pre_project.comment.service.CommentService;
import com.codestates.pre_project.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@Validated
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final MemberService memberService;

    public CommentController(CommentService commentService,
                             CommentMapper commentMapper,
                             MemberService memberService){
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentPostDto commentPostDto) {
        Comment comment = commentMapper.commentPostDtoToComment(commentPostDto);

        Comment createdComment = commentService.createComment(comment);

        return new ResponseEntity<>(commentMapper.commentToCommentResponseDto(createdComment), HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") @Positive long commentId,
                                        @Valid @RequestBody CommentPatchDto commentPatchDto) {
        commentPatchDto.setCommentId(commentId);
        Comment comment = commentService.updateComment(commentMapper.commentPatchDtoToComment(commentPatchDto));

        return new ResponseEntity<>(commentMapper.commentToCommentResponseDto(comment), HttpStatus.OK);
    }


    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") @Positive long commentId) {
        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(commentMapper.commentToCommentResponseDto(comment), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<Comment> pageComments = commentService.findComments(page - 1, size);
        List<Comment> comments = pageComments.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(commentMapper.commentsToCommentResponseDtos(comments), pageComments), HttpStatus.OK);
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

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
