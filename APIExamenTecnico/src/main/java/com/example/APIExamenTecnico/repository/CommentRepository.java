package com.example.APIExamenTecnico.repository;

import com.example.APIExamenTecnico.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByShowId(Long showId);
}