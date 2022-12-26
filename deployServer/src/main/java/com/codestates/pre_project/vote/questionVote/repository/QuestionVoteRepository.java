package com.codestates.pre_project.vote.questionVote.repository;

import com.codestates.pre_project.vote.questionVote.entity.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
    @Query(value = "SELECT sum(status) FROM QuestionVote")
    int sumByStatus();
}
