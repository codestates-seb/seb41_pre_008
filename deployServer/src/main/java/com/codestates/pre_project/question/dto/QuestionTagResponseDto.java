package com.codestates.pre_project.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class QuestionTagResponseDto {
    private long tagId;
    private String name;
}
