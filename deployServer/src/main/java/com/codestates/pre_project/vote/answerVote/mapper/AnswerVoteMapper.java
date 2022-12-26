package com.codestates.pre_project.vote.answerVote.mapper;

import com.codestates.pre_project.vote.answerVote.dto.AnswerVotePatchDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVotePostDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVoteResponseDto;
import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    AnswerVote postToAnswerVote(AnswerVotePostDto post);
    AnswerVote patchToAnswerVote(AnswerVotePatchDto patch);
    AnswerVoteResponseDto answerToResponseDto(AnswerVote answerVote);
}
