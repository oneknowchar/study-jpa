package hellojpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Member {
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy = "member")
	private Order order;
	
}
