package com.codestates.pre_project.vote.questionVote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionVotePatchDto {
    private long voteId;
    private long memberId;
    private int status;
}
