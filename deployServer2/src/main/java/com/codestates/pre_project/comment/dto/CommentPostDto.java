package com.codestates.pre_project.comment.dto;

import com.codestates.pre_project.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentPostDto {
    private long memberId;
    private long questionId;
    private long answerId;
    private String content;
    //private Comment.CommentType commentType;
}
