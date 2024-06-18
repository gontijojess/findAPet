package com.example.findAPet.repository;

import com.example.findAPet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByMemberId(Integer id);
}