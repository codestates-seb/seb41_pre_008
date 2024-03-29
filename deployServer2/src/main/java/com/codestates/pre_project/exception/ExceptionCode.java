package com.codestates.pre_project.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    QUESTIONVOTE_NOT_FOUND(404, "QuestionVote not found"),
    TAG_NOT_FOUND(404, "Tag not found"),
    CANNOT_DELETE_QUESTION(403, "Question can not delete"),
    ALREADY_DELETE_QUESTION(403, "Question already delete"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWERVOTE_NOT_FOUND(404, "AnswerVote not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
