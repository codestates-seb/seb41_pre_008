package com.codestates.pre_project.question.dto;

import com.codestates.pre_project.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuestionResponseDto {
    private long questionId;
    //private long memberId;
    private String title;
    private String problemContent;
    private String expectContent;
    private Question.QuestionStatus questionStatus;
    private List<QuestionTagResponseDto> questionTags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
