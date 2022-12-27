package com.codestates.pre_project.answer.mapper;

import com.codestates.pre_project.answer.dto.AnswerPatchDto;
import com.codestates.pre_project.answer.dto.AnswerPostDto;
import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.comment.dto.CommentResponseDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto){
        Answer answer = new Answer();
        Member member = new Member();
        Question question = new Question();
        answer.setAnswerContent(answerPostDto.getAnswerContent());
        member.setMemberId(answerPostDto.getMemberId());
        question.setQuestionId(answerPostDto.getQuestionId());
        answer.setMember(member);
        answer.setQuestion(question);
        return answer;
    }

    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    /*
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.nickName", target = "nickName")
    @Mapping(source = "question.questionId", target = "questionId")
    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

     */

    default AnswerResponseDto answerToAnswerResponseDto(Answer answer) {
        Member member = answer.getMember();
        List<Comment> comments = answer.getComments();
        List<CommentResponseDto> commentResponse
                = comments.stream()
                .filter(a -> a.getCommentType().equals(Comment.CommentType.ANSWER))
                .map(comment ->
                        new CommentResponseDto(comment.getCommentId(),
                                comment.getMember().getMemberId(),
                                comment.getMember().getNickName(),
                                comment.getQuestion().getQuestionId(),
                                comment.getAnswer().getAnswerId(),
                                comment.getContent(),
                                comment.getCreatedAt(),
                                comment.getModifiedAt()))
                .collect(Collectors.toList());

        return AnswerResponseDto.builder()
                .answerId(answer.getAnswerId())
                .memberId(answer.getMember().getMemberId())
                .nickName(answer.getMember().getNickName())
                .questionId(answer.getQuestion().getQuestionId())
                .answerContent(answer.getAnswerContent())
                .answerStatus(answer.getAnswerStatus())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .comments(commentResponse)
                .build();
    }
    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);
}
