package com.codestates.pre_project.vote.questionVote;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column(nullable = false)
    private int status;

    @OneToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
