package com.example.APIExamenTecnico.controller;

import com.example.APIExamenTecnico.dto.CommentRequestDto;
import com.example.APIExamenTecnico.model.ShowCache;
import com.example.APIExamenTecnico.repository.ShowCacheRepository;
import com.example.APIExamenTecnico.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final ShowCacheRepository showCacheRepository;

    public CommentController(CommentService commentService, ShowCacheRepository showCacheRepository) {
        this.commentService = commentService;
        this.showCacheRepository = showCacheRepository;
    }

    // Endpoint C: Guardar comentario y calificación
    @PostMapping
    public ResponseEntity<Map<String, String>> saveComment(@RequestBody CommentRequestDto request) {
        try {
            // 1. Validar si existe en Caché de Mongo
            Optional<ShowCache> cachedShow = showCacheRepository.findById(request.getShowId());
            if (cachedShow.isPresent()) {
                commentService.saveComment(request);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Collections.singletonMap("status", "Comentario guardado exitosamente"));
            } else {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("status", "El show indicado no existe, el comentario no pudo ser guardado"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("status", e.getMessage()));
        }
    }
}
