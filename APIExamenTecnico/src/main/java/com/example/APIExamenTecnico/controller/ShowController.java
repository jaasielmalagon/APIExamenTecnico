package com.example.APIExamenTecnico.controller;

import com.example.APIExamenTecnico.dto.ShowSearchResponseDto;
import com.example.APIExamenTecnico.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // Endpoint A & A-2: Búsqueda de shows
    @GetMapping("/search")
    public ResponseEntity<List<ShowSearchResponseDto>> searchShows(@RequestParam("search_query") String searchQuery) {
        return ResponseEntity.ok(showService.searchShows(searchQuery));
    }

    // Endpoint B, B-2 & B-3: Obtener información del show por ID
    @GetMapping("/shows/{show_id}")
    public ResponseEntity<Map<String, Object>> getShowById(@PathVariable("show_id") Long showId) {
        Map<String, Object> show = showService.getShowById(showId);
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(show);
    }
}
