package com.codestates.pre_project.answer.dto;

import com.codestates.pre_project.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class AnswerPatchDto {
    private long answerId;
    @Size(min = 30, max = 500, message = "답변 내용은 30자에서 500 사이여야 합니다.")
    private String answerContent;
    private Answer.AnswerStatus answerStatus;
}
