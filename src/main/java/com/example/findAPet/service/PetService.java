package com.example.findAPet.service;

import com.example.findAPet.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> getAll();
    Optional<Pet> getById(Integer id);
    void deleteById(Integer id);
    void save(Pet pet);
    Pet update(Integer id, Pet petAtualizado);
    List<Pet> findPetByMemberId(Integer id);
}