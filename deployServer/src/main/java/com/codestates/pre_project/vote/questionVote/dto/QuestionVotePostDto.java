package com.codestates.pre_project.vote.questionVote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionVotePostDto {
    private long questionId;
    private long memberId;
    private int status;
}
