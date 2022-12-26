package com.codestates.pre_project.comment.mapper;

import com.codestates.pre_project.comment.dto.CommentPatchDto;
import com.codestates.pre_project.comment.dto.CommentPostDto;
import com.codestates.pre_project.comment.dto.CommentResponseDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    //Comment commentPostDtoToComment(CommentPostDto commentPostDto);

    default Comment commentPostDtoToComment(CommentPostDto commentPostDto){
        Comment comment = new Comment();
        Member member = new Member();
        Question question = new Question();
        comment.setContent(commentPostDto.getContent());
        member.setMemberId(commentPostDto.getMemberId());
        question.setQuestionId(commentPostDto.getQuestionId());
        comment.setMember(member);
        comment.setQuestion(question);
        return comment;
    }

    Comment commentPatchDtoToComment(CommentPatchDto commentPatchDto);
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.nickName", target = "nickName")
    @Mapping(source = "question.questionId", target = "questionId")
    CommentResponseDto commentToCommentResponseDto(Comment comment);
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
