package hellojpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Team extends BaseEntity{
	@Id @GeneratedValue
	private Long id;
	private String teamName;
	
	@OneToMany(mappedBy = "team")
	private List<Member>members = new ArrayList<>();
	
}
