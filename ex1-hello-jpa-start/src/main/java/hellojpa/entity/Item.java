package hellojpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
	@Id @GeneratedValue
	private Long id;
	
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
}
