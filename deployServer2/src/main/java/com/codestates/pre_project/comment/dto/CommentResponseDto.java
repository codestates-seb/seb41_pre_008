package com.codestates.pre_project.comment.dto;

import com.codestates.pre_project.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {

    private long commentId;
    private long memberId;
    private String nickName;
    private long questionId;
    private long answerId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(long commentId,
                              long memberId,
                              String nickName,
                              long questionId,
                              long answerId,
                              String content,
                              LocalDateTime createdAt,
                              LocalDateTime modifiedAt){
        this.commentId = commentId;
        this.memberId = memberId;
        this.nickName = nickName;
        this.questionId = questionId;
        this.answerId = answerId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

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
