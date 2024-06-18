package com.example.findAPet.service.impl;

import com.example.findAPet.model.Species;
import com.example.findAPet.repository.SpeciesRepository;
import com.example.findAPet.service.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository speciesRepository;

    @Override
    public List<Species> getAll() {
        return speciesRepository.findAll();
    }

    @Override
    public Optional<Species> getById(Integer id) {
        return speciesRepository.findById(id);
    }
}
