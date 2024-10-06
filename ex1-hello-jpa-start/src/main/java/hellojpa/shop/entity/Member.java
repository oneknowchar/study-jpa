package hellojpa.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "SHOP_MEMBER")
@Table(name = "SHOP_MEMBER")
@Data
public class Member {
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	
	private String city;
	
	private String name;
	
	private String street;
	
	private String zipcode;
	
}
