package com.example.findAPet.service;

import com.example.findAPet.model.Member;
import com.example.findAPet.model.Pet;
import com.example.findAPet.model.Species;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> getAll();
    List<Member> filterByName(String Name);
    Optional<Member> getById(Integer id);
    void deleteById(Integer id);
    void save(Member member);
    Member update(Integer id, Member memberAtualizado);
    Member getByEmail(String email);
}
