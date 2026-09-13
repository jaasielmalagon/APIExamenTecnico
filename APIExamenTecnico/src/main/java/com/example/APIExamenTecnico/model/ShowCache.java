package com.example.APIExamenTecnico.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "show_cache")
public class ShowCache {
    @Id
    private Long id;
    private Object showData; // Guarda el objeto show completo devuelto por el API
}
