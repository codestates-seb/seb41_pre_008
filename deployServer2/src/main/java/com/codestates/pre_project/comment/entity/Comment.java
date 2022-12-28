package com.codestates.pre_project.comment.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType commentType;

     */

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    /*comment question 매핑 임시 삭제
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;



    public void setQuestion(Question question) {
        this.question = question;
        if (!this.question.getComments().contains(this)) {
            this.question.getComments().add(this);
        }
    }

     */

    /*
    @ManyToOne   // (1)
    @JoinColumn(name = "ANSWER_ID")  // (2)
    private Answer answer;

    public void setAnswer(Answer answer) {
        this.answer = answer;
        if (!this.answer.getComments().contains(this)) {
            this.answer.getComments().add(this);
        }
    }

     */
    /*
    public Comment(CommentType commentType, Member member, Question question, String content) {
        this.member = member;
        this.commentType = commentType;
        this.question = question;
        this.content = content;
    }

    public Comment(CommentType commentType, Member member, Answer answer, String content) {
        this.member = member;
        this.commentType = commentType;
        this.answer = answer;
        this.content = content;
    }

    public Comment(CommentType commentType, String content, Member member) {
        this.commentType = commentType;
        this.content = content;
    }

    public Comment(long commentId, Member member, String content, LocalDateTime createdAt,
                   LocalDateTime modifiedAt,  CommentType commentType) {
        this.commentId = commentId;
        this.commentType = commentType;
        this.member = member;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public enum CommentType {
        QUESTION,
        ANSWER
    }

     */

    /*
    public long getQuestionId() {
        long result = 0L;
        if (this.question != null) {
            result = this.question.getQuestionId();
        } else if (this.answer != null) {
            result = this.answer.getQuestion().getQuestionId();
        }
        return result;
    }

     */

}