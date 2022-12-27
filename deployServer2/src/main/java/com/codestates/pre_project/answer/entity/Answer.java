package com.codestates.pre_project.answer.entity;

import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private String answerContent;

    @Column(nullable = false)
    private int likes = 0;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_NOTSELECT;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne   // (1)
    @JoinColumn(name = "MEMBER_ID")  // (2)
    private Member member;

    public void addMember(Member member) {
        this.member = member;
    }

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

//    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private AnswerVote answerVote;


    public enum AnswerStatus {
        ANSWER_SELECT("답안 채택"),
        ANSWER_NOTSELECT("답안 채택 안됨");

        @Getter
        private String status;

        AnswerStatus(String status) {
            this.status = status;
        }
    }
}
