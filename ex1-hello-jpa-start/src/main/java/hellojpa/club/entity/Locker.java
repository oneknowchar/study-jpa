package hellojpa.club.entity;

import hellojpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Locker extends BaseEntity{
	@Id @GeneratedValue
	private Long id;
	
	@OneToOne(mappedBy = "locker")	//멤버 객체에 있는 'locker'변수에 매핑
	private String name;
}
