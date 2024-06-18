package com.example.findAPet.service.impl;

import com.example.findAPet.model.Pet;
import com.example.findAPet.repository.PetRepository;
import com.example.findAPet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> getById(Integer id) {
        return petRepository.findById(id);
    }

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void deleteById(Integer id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet update(Integer id, Pet petAtualizado) {
        petAtualizado.setId(id);
        return petRepository.save(petAtualizado);
    }

    @Override
    public List<Pet> findPetByMemberId(Integer id) {
        return petRepository.findByMemberId(id);
    }

//    @Override
//    public List<Pet> filterByRescueLocation(String location) {
//        List<Pet> all = getAll();
//        List<Pet> filtered = all.stream().filter(pet -> pet.getRescueLocation().toLowerCase().startsWith(location.toLowerCase())).toList();
//        return filtered;
//    }

}
