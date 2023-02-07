package com.codestates.pre_project.answer.repository;

import com.codestates.pre_project.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
