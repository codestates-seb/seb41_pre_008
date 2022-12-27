package com.codestates.pre_project.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class QuestionTagResponseDto {
    private long tagId;
    private String name;
}
