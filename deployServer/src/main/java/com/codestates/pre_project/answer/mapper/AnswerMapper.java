package com.codestates.pre_project.answer.mapper;

import com.codestates.pre_project.answer.dto.AnswerPatchDto;
import com.codestates.pre_project.answer.dto.AnswerPostDto;
import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer postToAnswer(AnswerPostDto post);
    Answer patchToAnswer(AnswerPatchDto patch);
    AnswerResponseDto answerToResponse(Answer answer);
    List<AnswerResponseDto> answersToResponses(List<Answer> answers);
}
