package com.codestates.pre_project.comment.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
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


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;


    public void setQuestion(Question question) {
        this.question = question;
        if (!this.question.getComments().contains(this)) {
            this.question.getComments().add(this);
        }
    }

    @ManyToOne   // (1)
    @JoinColumn(name = "ANSWER_ID")  // (2)
    private Answer answer;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}