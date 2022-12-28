package com.codestates.pre_project.comment.dto;

import com.codestates.pre_project.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private long commentId;
    private long memberId;
    private String nickName;
    private long questionId;
    //private long answerId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    /*
    private long commentId;
    private long memberId;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Comment.CommentType commentType;

     */


}
