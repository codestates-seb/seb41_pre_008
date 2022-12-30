package com.codestates.pre_project.question.dto;

import com.codestates.pre_project.question.entity.Question;
//import com.codestates.pre_project.question.entity.QuestionTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class QuestionPatchDto {
    @NotNull
    private long questionId;
    @Size(min = 15, max = 100, message = "질문 제목은 15자에서 100자 사이여야 합니다.")
    private String title;
    @Size(min = 30, max = 500, message = "질문 내용은 30자에서 500 사이여야 합니다.")
    private String problemContent;
    @Size(min = 30, max = 500, message = "질문 내용은 30자에서 500 사이여야 합니다.")
    private String expectContent;
    private Question.QuestionStatus questionStatus;
    private List<QuestionTagDto> questionTags;

    public void setQuestionId(long questionId){
        this.questionId = questionId;
    }
}
