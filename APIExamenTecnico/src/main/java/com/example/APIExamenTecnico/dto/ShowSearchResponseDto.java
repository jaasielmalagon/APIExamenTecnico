package com.example.APIExamenTecnico.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShowSearchResponseDto {
    private Long id;
    private String name;
    private String channel;
    private String summary;
    private List<String> genres;
    private List<CommentResponseDto> comments;
}