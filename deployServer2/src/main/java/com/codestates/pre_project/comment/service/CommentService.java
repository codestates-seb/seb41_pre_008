package com.codestates.pre_project.comment.service;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.answer.service.AnswerService;
import com.codestates.pre_project.comment.dto.CommentPostDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.comment.repository.CommentRepository;
import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.question.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    private final MemberService memberService;
    private final CommentRepository commentRepository;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public CommentService(CommentRepository commentRepository,
                          MemberService memberService,
                          QuestionService questionService,
                          AnswerService answerService){
        this.memberService = memberService;
        this.commentRepository = commentRepository;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public Comment createComment(Comment comment){
        /*
        if(comment.getQuestion().getQuestionId() > 0)
            verifyComment1(comment);
        else if(comment.getAnswer().getAnswerId() > 0)
            verifyComment2(comment);

         */
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment){
        Comment findComment = findVerifiedComment(comment.getCommentId());

        Optional.ofNullable(comment.getContent())
                .ifPresent(content -> findComment.setContent(content));

        findComment.setModifiedAt(LocalDateTime.now());

        return commentRepository.save(findComment);
    }

    public Comment findComment(long commentId){
        return findVerifiedComment(commentId);
    }

    public Page<Comment> findComments(int page, int size){
        return commentRepository.findAll
                (PageRequest.of(page, size, Sort.by("commentId").descending()));
    }

    public void deleteComment(long commentId){
        Comment findComment = findVerifiedComment(commentId);

        commentRepository.delete(findComment);
    }
    private void verifyComment1(Comment comment) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(comment.getMember().getMemberId());
        // 질문이 존재하는지 확인
        questionService.findVerifiedQuestion(comment.getQuestion().getQuestionId());
    }


    private void verifyComment2(Comment comment){
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(comment.getMember().getMemberId());
        // 답변이 존재하는지 확인
        answerService.findVerifiedAnswer(comment.getAnswer().getAnswerId());
    }


    public Comment findVerifiedComment(long commentId){
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);
        Comment findComment =
                optionalComment.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return findComment;
    }
}
