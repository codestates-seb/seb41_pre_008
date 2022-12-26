package com.codestates.pre_project.vote.answerVote.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column(nullable = false)
    private int status;

    @OneToOne
    @JoinColumn(name = "ANSWER_ID", nullable = false)
    private Answer answer;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;
}
