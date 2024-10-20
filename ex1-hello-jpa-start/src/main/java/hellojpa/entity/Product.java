package hellojpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
	@Id @GeneratedValue
	private Long id;
	private String name;
	private int price;
	private int stockAmount;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
}
