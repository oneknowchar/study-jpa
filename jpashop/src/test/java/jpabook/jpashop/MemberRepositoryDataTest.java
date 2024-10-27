package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;

@SpringBootTest
public class MemberRepositoryDataTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional //이 어노테이션은 테스트가 끝난 후 롤백시킨다. 따라서 데이터 없음
	@Rollback(value = false)
	public void testMember() {
		// given
		Member member = new Member();
		member.setName("user00");
		Long saveMember = memberRepository.saveMember(member); // 변수 추출 방법, Alt + Shift + L

		// when
		Member findMember = memberRepository.findMember(saveMember);

		// then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
	}
}