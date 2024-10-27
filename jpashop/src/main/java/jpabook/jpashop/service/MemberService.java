package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true) //전체 적용. 조회 성능 최적화!
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	@Transactional	//조회가 아닌것은 readOnly = false(default 옵션 적용)
	public Long join(Member member) {
		validateDuplicatemember(member);	//중복 회원 검증
		memberRepository.saveMember(member);
		return member.getId();
	}

	private void validateDuplicatemember(Member member) {
		//이름이 같다면? 예제에선 userName을 사용했지만 실무는 유니크한 값을 사용하길! ex)회원번호 등
		List<Member> findMembers = memberRepository.findByName(member.getName());	
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
		
	}
	
	/**
	 * 회원 전체 조회
	 * @return
	 */
	public List<Member>findMembers(){
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
	
	
}
