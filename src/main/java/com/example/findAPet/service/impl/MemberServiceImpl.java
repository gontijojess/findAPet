package com.example.findAPet.service.impl;

import com.example.findAPet.model.Member;
import com.example.findAPet.model.Pet;
import com.example.findAPet.repository.MemberRepository;
import com.example.findAPet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> getAll(){
        return memberRepository.findAll();
    }
    @Override
    public Optional<Member> getById(Integer id){
        return memberRepository.findById(id);
    }
    @Override
    public void save(Member member){
        memberRepository.save(member);
    }
    @Override
    public void deleteById(Integer id){
        memberRepository.deleteById(id);
    }
    @Override
    public Member update(Integer id, Member memberAtualizado){
        memberAtualizado.setId(id);
        return memberRepository.save(memberAtualizado);
    }
    @Override
    public Member getByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    @Override
    public List<Member> filterByName(String name){
        List<Member> all = getAll();
        List<Member> filtered = all.stream().filter(member -> member.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
        return filtered;
    }

}