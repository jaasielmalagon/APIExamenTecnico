package com.example.APIExamenTecnico.repository;

import com.example.APIExamenTecnico.model.ShowCache;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowCacheRepository extends MongoRepository<ShowCache, Long> {
}
