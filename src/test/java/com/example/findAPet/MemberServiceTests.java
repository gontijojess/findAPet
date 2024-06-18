package com.example.findAPet;

import com.example.findAPet.model.Member;
import com.example.findAPet.model.Pet;
import com.example.findAPet.model.Species;
import com.example.findAPet.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName(value="Deve inserir um usuário na lista")
    public void testInsert() {
        List<Member> all = memberService.getAll();
        int estadoInicial = all.size();
        Member member = new Member();
        member.setName("Marcelo Alves");
        member.setEmail("marcelo@gmail.com");
        member.setPassword("abcd123456");
        member.setNumber("51999999999");
        memberService.save(member);
        all = memberService.getAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @Transactional
    @DisplayName(value="Deve deletar um membro do banco")
    public void testDelete(){
        Member member = new Member();
        member.setName("Marcelo Alves");
        member.setEmail("marcelo@gmail.com");
        member.setPassword("abcd123456");
        member.setNumber("51999999999");
        Pet pet = new Pet();
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        member.setPets(pets);
        memberService.save(member);
        List<Member> all = memberService.getAll();
        int estadoInicial = all.size();
        Member member2 = all.get(0);
        memberService.deleteById(member2.getId());
        all = memberService.getAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial - 1, estadoFinal);
    }

    @Test
    @Transactional
    @DisplayName(value="Deve retornar um membro pelo ID")
    public void testGetById(){
        Member member = new Member();
        member.setName("Marcelo Alves");
        member.setEmail("marcelo@gmail.com");
        member.setPassword("abcd123456");
        member.setNumber("51999999999");
        Pet pet = new Pet();
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        member.setPets(pets);
        memberService.save(member);
        List<Member> all = memberService.getAll();
        Member member2 = all.get(0);
        Optional<Member> byId = memberService.getById(member2.getId());
        assertTrue(byId.isPresent());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar todos os membros do banco")
    public void testGetAll() {
        List<Member> all = memberService.getAll();
        int estadoInicial = all.size();
        Member member1 = new Member();
        member1.setName("Marcelo Alves");
        member1.setEmail("marcelo@gmail.com");
        member1.setPassword("abcd123456");
        member1.setNumber("51999999999");
        Pet pet = new Pet();
        pet.setMember(member1);
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        member1.setPets(pets);
        memberService.save(member1);

        Member member2 = new Member();
        member2.setName("Marcela Machado");
        member2.setEmail("marcela@gmail.com");
        member2.setPassword("abcd78978");
        member2.setNumber("51999999888");
        Pet pet2 = new Pet();
        pet2.setMember(member2);
        List<Pet> pets2 = new ArrayList<>();
        pets2.add(pet2);
        member2.setPets(pets2);
        memberService.save(member2);

        all = memberService.getAll();
        int estadoFinal = all.size();

        List<Member> allMembers = memberService.getAll();
        assertEquals(estadoInicial + 2, estadoFinal);
        assertTrue(allMembers.contains(member1));
        assertTrue(allMembers.contains(member2));
    }

    @Test
    @Transactional
    @DisplayName("Deve atualizar um membro no banco")
    public void testUpdate() {
        Member member = new Member();
        member.setName("Marcelo Alves");
        member.setEmail("marcelo@gmail.com");
        member.setPassword("abcd123456");
        member.setNumber("51999999999");
        Pet pet = new Pet();
        pet.setMember(member);
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        member.setPets(pets);
        memberService.save(member);

        List<Member> all = memberService.getAll();
        int estadoInicial = all.size();

        Member updatedMember = new Member();
        updatedMember.setName("Marcelo Machado");
        updatedMember.setEmail("marcelo@gmail.com");
        updatedMember.setPassword("abcd123456");
        updatedMember.setNumber("51999999999");
        pets.add(pet);
        updatedMember.setPets(pets);
        memberService.update(member.getId(), updatedMember);

        List<Member> allMembers = memberService.getAll();
        int estadoFinal = allMembers.size();
        Member members = allMembers.get(allMembers.size() - 1);
        assertEquals(   estadoInicial, estadoFinal);
        assertEquals("Marcelo Machado", members.getName());
    }

    @Test
    @DisplayName("Deve retornar um membro pelo email")
    public void testGetByEmail() {
        Member member = new Member();
        member.setName("Marcelo Alves");
        member.setEmail("marcelo@gmail.com");
        member.setPassword("abcd123456");
        member.setNumber("51999999999");
        memberService.save(member);

        Member foundMember = memberService.getByEmail("marcelo@gmail.com");

        assertNotNull(foundMember);
        assertEquals("Marcelo Alves", foundMember.getName());
        assertEquals("marcelo@gmail.com", foundMember.getEmail());
    }

}
