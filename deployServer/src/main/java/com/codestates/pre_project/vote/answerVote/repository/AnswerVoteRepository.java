package com.codestates.pre_project.vote.answerVote.repository;

import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}
