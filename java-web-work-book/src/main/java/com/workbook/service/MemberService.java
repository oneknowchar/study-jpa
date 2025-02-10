package com.workbook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.workbook.entity.Member;
import com.workbook.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public List<Member> findTodos() {

		return memberRepository.find().stream().collect(Collectors.toList());
	}

	public Member findById(Long tno) {
		return memberRepository.findById(tno);
	}

	public void register(Member member) {
		memberRepository.register(member);
	}

	public void remove(Member member) {
		memberRepository.deleteByRemove(member);
	}

}
