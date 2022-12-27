package com.codestates.pre_project.question.dto;

import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.comment.dto.CommentResponseDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuestionResponseDto {
    private long questionId;
    private long memberId;
    private String nickName;
    private String title;
    private String problemContent;
    private String expectContent;
    private Question.QuestionStatus questionStatus;
    private List<QuestionTagResponseDto> questionTags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;
    private List<AnswerResponseDto> answers;
}
