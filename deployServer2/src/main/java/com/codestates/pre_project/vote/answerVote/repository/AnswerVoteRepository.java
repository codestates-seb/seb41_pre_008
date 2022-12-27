package com.codestates.pre_project.vote.answerVote.repository;

import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    @Query(value = "SELECT SUM(STATUS) FROM ANSWER_VOTE WHERE ANSWER_ID = :answerId", nativeQuery = true)
    int sumByStatus(long answerId);

    @Query(value = "SELECT * FROM ANSWER_VOTE WHERE ANSWER_ID = :answerId AND MEMBER_ID = :memberId", nativeQuery = true)
    Optional<AnswerVote> findByMemberIdAndAnswerId(long answerId, long memberId);
    @Query(value = "SELECT * FROM ANSWER_VOTE WHERE ANSWER_ID= :answerId", nativeQuery = true)
    List<AnswerVote> findAllByAnswerId(long answerId);
}
