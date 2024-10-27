package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable  
@Getter
@AllArgsConstructor
//  값 타입은 변경 불가능하게 설정, @Setter 제거하고 생성자에서 초기화해서 변경 불가능한 클래스를 만들어라.
//  @Setter 직접 세팅은 피한다! Member에서 항상 new Address로 초기화!
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
	//JPA 특성상 리플랙션 기술들을 사용하기에 기본생성자는 필수!
	//개발자가 기본생성자를 만들 일이 없기 때문에  protected
	protected Address() {
	}
}
