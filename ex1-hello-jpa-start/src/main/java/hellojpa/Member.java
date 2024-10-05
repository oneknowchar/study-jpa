package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
//테이블명 맵핑
@Table(name = "Member")	
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
	@SequenceGenerator(name = "member_seq_gen", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 50)
	private Long id;
	
	//컬럼명 맵핑
	//@Column(name ="nickname")	
	private String name;
	
	public Member() {
		super();
	}

	public Member(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + "]";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
