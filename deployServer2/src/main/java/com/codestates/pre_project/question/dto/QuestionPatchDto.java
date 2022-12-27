package com.codestates.pre_project.question.dto;

import com.codestates.pre_project.question.entity.Question;
//import com.codestates.pre_project.question.entity.QuestionTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class QuestionPatchDto {
    @NotNull
    private long questionId;
    private String title;
    private String problemContent;
    private String expectContent;
    private Question.QuestionStatus questionStatus;
    private List<QuestionTagDto> questionTags;

    public void setQuestionId(long questionId){
        this.questionId = questionId;
    }
}
