package com.example.APIExamenTecnico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDto {
    private String comment;
    private Integer rating;
}
