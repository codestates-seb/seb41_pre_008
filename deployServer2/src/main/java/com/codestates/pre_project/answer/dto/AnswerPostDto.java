package com.codestates.pre_project.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerPostDto {
    private long memberId;
    private long questionId;
    @Size(min = 30, max = 500, message = "답변 내용은 30자에서 500 사이여야 합니다.")
    private String answerContent;
}
