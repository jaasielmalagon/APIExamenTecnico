package com.example.APIExamenTecnico.service;

import com.example.APIExamenTecnico.dto.ShowSearchResponseDto;
import com.example.APIExamenTecnico.dto.TvMazeSearchItemDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final RestTemplate restTemplate;

    public ShowService(RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }
    // A & A-2: Búsqueda de shows enriquecida con comentarios
    public List<ShowSearchResponseDto> searchShows(String query) {
        String url = "http://api.tvmaze.com/search/shows?q=" + query;
        TvMazeSearchItemDto[] response = restTemplate.getForObject(url, TvMazeSearchItemDto[].class);

        if (response == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(response).map(item -> {
            TvMazeSearchItemDto.ShowDetail show = item.getShow();

            // Determina el canal (network_name o webchannel_name)
            String channel = null;
            if (show.getNetwork() != null) {
                channel = show.getNetwork().getName();
            } else if (show.getWebChannel() != null) {
                channel = show.getWebChannel().getName();
            }

            return ShowSearchResponseDto.builder()
                    .id(show.getId())
                    .name(show.getName())
                    .channel(channel)
                    .summary(show.getSummary())
                    .genres(show.getGenres())
                    .build();
        }).collect(Collectors.toList());
    }

    // B, B-2 & B-3: Obtener show con caché en MongoDB Atlas y comentarios agregados
    @SuppressWarnings("unchecked")
    public Map<String, Object> getShowById(Long showId) {
        Map<String, Object> showDataMap;
        //Consumir API externa buscando el show por id
        String url = "https://api.tvmaze.com/shows/" + showId;
        showDataMap = restTemplate.getForObject(url, Map.class);
        return showDataMap;
    }
}
