package com.codestates.pre_project.answer.dto;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.comment.dto.CommentResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDto {
    private long answerId;
    private long memberId;
    private String nickName;
    private long questionId;
    private String answerContent;
    private Answer.AnswerStatus answerStatus;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;

}
