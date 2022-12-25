package com.codestates.pre_project.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerPostDto {
    private long memberId;
    private String answerContent;
}
