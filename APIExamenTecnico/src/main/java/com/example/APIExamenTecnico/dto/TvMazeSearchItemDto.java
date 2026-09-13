package com.example.APIExamenTecnico.dto;

import lombok.Data;

import java.util.List;

@Data
public class TvMazeSearchItemDto {
    private ShowDetail show;

    @Data
    public static class ShowDetail {
        private Long id;
        private String name;
        private Network network;
        private Network webChannel;
        private String summary;
        private List<String> genres;
    }

    @Data
    public static class Network {
        private String name;
    }
}