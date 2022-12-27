package com.codestates.pre_project.vote.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private VoteStatus voteStatus;
    public enum VoteStatus {
        
        VOTE_UP("유용한 문의 또는 답변"),
        VOTE_DOWN("유용하지 않은 문의 또는 답변");

        @Getter
        private String status;

        VoteStatus(String status) {
            this.status = status;
        }
    }
}


