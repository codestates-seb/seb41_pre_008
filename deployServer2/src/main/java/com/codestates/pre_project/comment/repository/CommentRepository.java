package com.codestates.pre_project.comment.repository;

import com.codestates.pre_project.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
