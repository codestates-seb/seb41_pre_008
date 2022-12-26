package com.codestates.pre_project.comment.dto;

import lombok.Getter;

@Getter
public class CommentPostDto {
    private long memberId;
    private long questionId;
    private String content;
}
