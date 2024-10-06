package hellojpa.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	
}
