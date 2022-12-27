package com.codestates.pre_project.vote.questionVote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionVoteResponseDto {
    private long voteId;
    private long memberId;
    private int status;
}
