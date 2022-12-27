package com.codestates.pre_project.vote.answerVote.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column(nullable = false)
    private int status;

    @OneToOne
    @JoinColumn(name="ANSWER_ID")
    private Answer answer;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
