package com.example.APIExamenTecnico.service;

import com.example.APIExamenTecnico.dto.ShowSearchResponseDto;
import com.example.APIExamenTecnico.dto.TvMazeSearchItemDto;
import com.example.APIExamenTecnico.model.ShowCache;
import com.example.APIExamenTecnico.repository.ShowCacheRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final RestTemplate restTemplate;
    private final ShowCacheRepository showCacheRepository;

    public ShowService(RestTemplate restTemplate, ShowCacheRepository showCacheRepository
    ) {
        this.restTemplate = restTemplate;
        this.showCacheRepository = showCacheRepository;
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

        // 1. Validar si existe en Caché de Mongo
        Optional<ShowCache> cachedShow = showCacheRepository.findById(showId);
        System.out.println(cachedShow);
        if (cachedShow.isPresent()) {
            System.out.println("Se ha encontrado el show en caché!");
            showDataMap = (Map<String, Object>) cachedShow.get().getShowData();
        } else {
            System.out.println("No se ha encontrado el show en caché, consumiendo API externa...");
            // 2. Si no existe, consumir API externa
            String url = "https://api.tvmaze.com/shows/" + showId;
            showDataMap = restTemplate.getForObject(url, Map.class);

            // 3. Guardar en MongoDB Atlas antes de retornar respuesta
            if (showDataMap != null) {
                ShowCache cache = new ShowCache();
                cache.setId(showId);
                cache.setShowData(showDataMap);
                showCacheRepository.save(cache);
                System.out.println("Se ha guardado el registro en caché de MongoDB Atlas");
            }
        }

        return showDataMap;
    }
}
