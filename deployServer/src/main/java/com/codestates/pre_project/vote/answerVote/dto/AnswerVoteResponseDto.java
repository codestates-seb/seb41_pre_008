package com.codestates.pre_project.vote.answerVote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerVoteResponseDto {
    private long answerVoteId;
    private long memberId;
    private int status;
}