package com.example.APIExamenTecnico.service;

import com.example.APIExamenTecnico.dto.CommentRequestDto;
import com.example.APIExamenTecnico.dto.CommentResponseDto;
import com.example.APIExamenTecnico.model.Comment;
import com.example.APIExamenTecnico.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(CommentRequestDto dto) {
        if (dto.getRating() == null || dto.getRating() < 0 || dto.getRating() > 5) {
            throw new IllegalArgumentException("El rating debe ser un valor entre 0 y 5.");
        }
        Comment comment = new Comment(null, dto.getShowId(), dto.getComment(), dto.getRating());
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getCommentsByShowId(Long showId) {
        return commentRepository.findByShowId(showId)
                .stream()
                .map(c -> new CommentResponseDto(c.getComment(), c.getRating()))
                .collect(Collectors.toList());
    }
}
