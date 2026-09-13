package com.example.APIExamenTecnico.dto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long showId;
    private String comment;
    private Integer rating;
}
