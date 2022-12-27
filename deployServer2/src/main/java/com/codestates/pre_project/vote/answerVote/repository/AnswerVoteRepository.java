package com.codestates.pre_project.vote.answerVote.repository;

import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    @Query(value = "SELECT sum(status) FROM AnswerVote")
    int sumByStatus();
}
