package hellojpa.club.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name="CLUB_TEAM")
@Table(name="CLUB_TEAM")
@Data
public class Team {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")	//joinColum의 값
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "team")	//Member 클래스의 변수명 team 에 매핑됨
	private List<Member> members = new ArrayList<>();	//null 방지, 초기화 해준다.

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", membersCnt=" + members.size() + "]";
	}
	
	
	
}
