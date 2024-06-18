package com.example.findAPet;

import com.example.findAPet.model.Species;
import com.example.findAPet.service.SpeciesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SpeciesServiceTests {
    @Autowired
    SpeciesService speciesService;

    @Test
    @DisplayName("Deve retornar todas as espécies")
    public void testGetAll(){
        List<Species> all = speciesService.getAll();
        assertEquals(3, all.size());
    }

    @Test
    @DisplayName("Deve retornar a espécie por ID")
    public void testGetById(){
        Optional<Species> byId = speciesService.getById(1);
        assertTrue(byId.isPresent());
        Optional<Species> naoExistente = speciesService.getById(1000);
        assertTrue(naoExistente.isEmpty());
    }
}
