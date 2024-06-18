package com.example.findAPet;

import com.example.findAPet.model.Member;
import com.example.findAPet.model.Pet;
import com.example.findAPet.model.Species;
import com.example.findAPet.service.PetService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PetServiceTests {

    @Autowired
    PetService petService;

    @Test
    @DisplayName(value="Deve inserir um animal na lista")
    public void testInsert() {
        Species species = Species.builder().id(1).build();
        Member member = Member.builder().id(1).build();
        List<Pet> all = petService.getAll();
        int estadoInicial = all.size();
        Pet pet = new Pet();
        pet.setDescription("Branco e preto porte médio");
        pet.setSex("Macho");
        pet.setRescueLocation("Porto Alegre");
        pet.setSpecies(species);
        pet.setMember(member);
        pet.setUrlPicture("http://meupet.com/123");
        petService.save(pet);
        all = petService.getAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @DisplayName(value="Deve deletar um animal do banco")
    public void testDelete(){
        Species species = Species.builder().id(1).build();
        Member member = Member.builder().id(1).build();
        Pet pet = new Pet();
        pet.setDescription("Branco e preto porte médio");
        pet.setSex("Macho");
        pet.setRescueLocation("Porto Alegre");
        pet.setSpecies(species);
        pet.setMember(member);
        pet.setUrlPicture("http://meupet.com/123");
        petService.save(pet);
        List<Pet> all = petService.getAll();
        int estadoInicial = all.size();
        Pet pet2 = all.get(0);
        petService.deleteById(pet2.getId());
        all = petService.getAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial - 1, estadoFinal);
    }

    @Test
    @DisplayName(value="Deve retornar um animal pelo ID")
    public void testGetById(){
        Species species = Species.builder().id(1).build();
        Member member = Member.builder().id(1).build();
        Pet pet = new Pet();
        pet.setDescription("Branco e preto porte médio");
        pet.setSex("Macho");
        pet.setRescueLocation("Porto Alegre");
        pet.setSpecies(species);
        pet.setMember(member);
        pet.setUrlPicture("http://meupet.com/123");
        petService.save(pet);
        List<Pet> all = petService.getAll();
        Pet pet2 = all.get(0);
        Optional<Pet> byId = petService.getById(pet2.getId());
        assertTrue(byId.isPresent());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar todos os animais do banco")
    public void testGetAll() {
        List<Pet> all = petService.getAll();
        Species species = Species.builder().id(1).build();
        Member member = Member.builder().id(1).build();
        int estadoInicial = all.size();
        Pet pet1 = new Pet();
        pet1.setDescription("Branco e preto porte médio");
        pet1.setSex("Macho");
        pet1.setRescueLocation("Porto Alegre");
        pet1.setSpecies(species);
        pet1.setMember(member);
        pet1.setUrlPicture("http://meupet.com/123");
        petService.save(pet1);

        Pet pet2 = new Pet();
        pet2.setDescription("Branca porte pequeno");
        pet2.setSex("Fêmea");
        pet2.setRescueLocation("Porto Alegre");
        pet2.setSpecies(species);
        pet2.setMember(member);
        pet2.setUrlPicture("http://meupet.com/456");
        petService.save(pet2);

        all = petService.getAll();
        int estadoFinal = all.size();

        List<Pet> allPets = petService.getAll();
        assertEquals(estadoInicial + 2, estadoFinal);
        assertTrue(allPets.contains(pet1));
        assertTrue(allPets.contains(pet2));
    }

    @Test
    @DisplayName("Deve atualizar um animal no banco")
    public void testUpdate() {
        Species species = Species.builder().id(1).build();
        Member member = Member.builder().id(1).build();
        Pet pet = new Pet();
        pet.setDescription("Branco e preto porte médio");
        pet.setSex("Macho");
        pet.setRescueLocation("Porto Alegre");
        pet.setSpecies(species);
        pet.setMember(member);
        pet.setUrlPicture("http://meupet.com/123");
        petService.save(pet);

        List<Pet> all = petService.getAll();
        int estadoInicial = all.size();

        Pet updatedPet = new Pet();
        updatedPet.setDescription("Branco e preto porte PEQUENO");
        updatedPet.setSex("Macho");
        updatedPet.setRescueLocation("Porto Alegre");
        updatedPet.setSpecies(species);
        updatedPet.setMember(member);
        updatedPet.setUrlPicture("http://meupet.com/123");
        petService.update(pet.getId(), updatedPet);

        List<Pet> allPets = petService.getAll();
        int estadoFinal = allPets.size();
        Pet pets = allPets.get(allPets.size() - 1);
        assertEquals(   estadoInicial, estadoFinal);
        assertEquals(pet.getId(), updatedPet.getId());
        assertEquals("Branco e preto porte PEQUENO", pets.getDescription());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar todos os pets associados a um membro")
    public void testGetAllPetsForMember() {
        Species species = Species.builder().id(1).build();
        Member member = Member.builder().id(1).build();
        Pet pet1 = new Pet();
        pet1.setDescription("Branco e preto porte médio");
        pet1.setSex("Macho");
        pet1.setRescueLocation("Porto Alegre");
        pet1.setSpecies(species);
        pet1.setMember(member);
        pet1.setUrlPicture("http://meupet.com/123");
        petService.save(pet1);

        Pet pet2 = new Pet();
        pet2.setDescription("Branca porte pequeno");
        pet2.setSex("Fêmea");
        pet2.setRescueLocation("Porto Alegre");
        pet2.setSpecies(species);
        pet2.setMember(member);
        pet2.setUrlPicture("http://meupet.com/456");
        petService.save(pet2);

        List<Pet> petsForMember = petService.findPetByMemberId(member.getId());
        assertTrue(petsForMember.contains(pet1));
        assertTrue(petsForMember.contains(pet1));
        assertEquals("Branco e preto porte médio", petsForMember.get(petsForMember.size() - 2).getDescription());
        assertEquals("Fêmea", petsForMember.get(petsForMember.size() - 1).getSex());
    }

}
