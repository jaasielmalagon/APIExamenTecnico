package com.example.APIExamenTecnico.controller;

import com.example.APIExamenTecnico.dto.ShowSearchResponseDto;
import com.example.APIExamenTecnico.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
