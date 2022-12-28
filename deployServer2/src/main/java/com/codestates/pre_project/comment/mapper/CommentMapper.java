package com.codestates.pre_project.comment.mapper;

import com.codestates.pre_project.answer.entity.Answer;
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
    /*
    Comment commentPostDtoToComment(CommentPostDto commentPostDto);
    default Comment commentPostToComment(CommentPostDto commentPostDto) {
        Comment comment = new Comment();
        comment.setContent(commentPostDto.getContent());
        Member member = new Member();
        member.setMemberId(commentPostDto.getMemberId());
        comment.setMember(member);
        return comment;
    }

     */


    default Comment commentPostDtoToComment(CommentPostDto commentPostDto){
        Comment comment = new Comment();
        Member member = new Member();

        /*
        if(commentPostDto.getAnswerId()>0){
            System.out.println(commentPostDto.getAnswerId());
            Answer answer = new Answer();
            comment.setContent(commentPostDto.getContent());
            member.setMemberId(commentPostDto.getMemberId());
            answer.setAnswerId(commentPostDto.getAnswerId());
            comment.setMember(member);
            comment.setAnswer(answer);
            System.out.println("finish");
            return comment;
        }

         */
        System.out.println(commentPostDto.getQuestionId());
        Question question = new Question();
        comment.setContent(commentPostDto.getContent());
        member.setMemberId(commentPostDto.getMemberId());
        question.setQuestionId(commentPostDto.getQuestionId());
        comment.setMember(member);
        comment.setQuestion(question);
        System.out.println("finish");
        return comment;


    }

    Comment commentPatchDtoToComment(CommentPatchDto commentPatchDto);

    /*
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.nickName", target = "nickName")
    CommentResponseDto commentToCommentResponseDto(Comment comment);

     */


    default CommentResponseDto commentToCommentResponseDto(Comment comment) {
        Member member = comment.getMember();

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .memberId(member.getMemberId())
                .nickName(member.getNickName())
                .questionId(comment.getQuestion().getQuestionId())
                //.answerId(comment.getAnswer().getAnswerId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
    List<CommentResponseDto> commentsToCommentResponseDtos(List<Comment> comments);
}
