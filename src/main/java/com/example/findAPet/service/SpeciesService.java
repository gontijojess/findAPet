package com.example.findAPet.service;

import com.example.findAPet.model.Species;

import java.util.List;
import java.util.Optional;

public interface SpeciesService {
    List<Species> getAll();
    Optional<Species> getById(Integer id);
}
