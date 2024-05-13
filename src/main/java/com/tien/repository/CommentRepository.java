package com.tien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tien.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
}
