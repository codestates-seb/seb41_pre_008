package com.codestates.pre_project.comment.dto;

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
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
