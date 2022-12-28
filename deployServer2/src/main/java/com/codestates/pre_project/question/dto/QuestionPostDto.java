package com.codestates.pre_project.question.dto;

//import com.codestates.pre_project.question.entity.QuestionTag;
import com.codestates.pre_project.question.entity.QuestionTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionPostDto {

    @Positive
    private long memberId;

    private String title;
    private String problemContent;
    private String expectContent;

    private List<QuestionTagDto> questionTags;
}
