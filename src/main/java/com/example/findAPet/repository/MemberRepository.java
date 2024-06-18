package com.example.findAPet.repository;

import com.example.findAPet.model.Member;
import com.example.findAPet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email);
}
