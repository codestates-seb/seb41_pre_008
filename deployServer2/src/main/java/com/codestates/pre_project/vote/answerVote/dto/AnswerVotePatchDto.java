package com.codestates.pre_project.vote.answerVote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerVotePatchDto {
    private long voteId;
    private long memberId;
    private int status;
}
