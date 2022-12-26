package com.codestates.pre_project.answer.dto;

import com.codestates.pre_project.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerResponseDto {
    private long answerId;
    private String answerContent;
    private Answer.AnswerStatus answerStatus;
}
