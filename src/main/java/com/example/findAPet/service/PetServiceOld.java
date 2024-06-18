package com.example.findAPet.service;

import com.example.findAPet.exception.AttributeValidationException;
import com.example.findAPet.exception.ResourceNotFoundException;
import com.example.findAPet.model.Pet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PetServiceOld {
    static List<Pet> pets = initValues();

    public PetServiceOld() {
    }

    private static List<Pet> initValues() {
        ArrayList<Pet> petsl = new ArrayList();
        /* petsl.add(new Pet(0, "Cachorro", "Fêmea", "SRD, porte P, toda caramelo", "Bairro Cinco Colônias - Canoas", "http://imagempet.com/123.jpg", "61999675644"));
        petsl.add(new Pet(1, "Cachorro", "Macho", "SRD, porte médio, preto", "Eldorado do Sul", "http://imagempet.com/456.jpg", "1199343455")); */
        return petsl;
    }

    public List<Pet> getAll() {
        return pets;
    }

    public Pet getById(int id) {
        if (id < 0) {
            throw new ResourceNotFoundException("Id invalido - deve ser um número positivo");
        } else {
            Optional<Pet> petOpt = pets.stream().filter(pet -> pet.getId() == id).findFirst();
            if(petOpt.isEmpty()) throw new ResourceNotFoundException("Ocorreu um erro. Animal não encontrado");
            return petOpt.get();
        }
    }

    public void save(Pet pet) {
        if (pet.getId() < 0) {
            throw new AttributeValidationException("Id invalido - deve ser um número positivo");
        }
        if (pet.getRescueLocation() == null || pet.getRescueLocation().isBlank()) {
            throw new AttributeValidationException("O campo 'Local de Resgate' precisa ser preenchido");
        }
        if (pet.getUrlPicture() == null || pet.getUrlPicture().isBlank()) {
            throw new AttributeValidationException("O campo 'Foto' precisa ser preenchido");
        }
        pets.add(pet);
    }

//    public List<Pet> filterBySpecies(String species) {
//        List<Pet> all = getAll();
//        List<Pet> filtered = all.stream().filter(pet -> pet.getSpecies().toLowerCase().startsWith(species.toLowerCase())).toList();
//        return filtered;
//    }

    public void update(Integer id, Pet updatedPet) {
        if (resourceNotFound(id)) {
            throw new ResourceNotFoundException("Ocorreu um erro. Animal não encontrado");
        }
        pets.set(id, updatedPet);
    }

    public void deleteById(Integer id) {
        if (resourceNotFound(id)) {
            throw new ResourceNotFoundException("Ocorreu um erro. Animal não encontrado");
        }
        pets.remove(pets.get(id));
    }

    private static boolean resourceNotFound(Integer id) {
        return pets.stream().filter(pet -> pet.getId() == id).findFirst().isEmpty();
    }
}
