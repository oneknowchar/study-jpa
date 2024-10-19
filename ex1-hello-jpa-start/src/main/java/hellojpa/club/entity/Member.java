package hellojpa.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "CLUB_MEMBER")
@Table(name = "CLUB_MEMBER")
@Data
public class Member {
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String userName;

	@ManyToOne(fetch = FetchType.EAGER) //default
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
	
	@OneToOne @JoinColumn(name = "LOCKER_ID")
	private Locker locker;
	
	public void chageTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);	//연관관계 편의 메서드 필히 생성
	}
	//인프런 연관 관계의 주인! 많은 쪽이 주인이다. 1쪽은 그저 조회만!!!
//https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21697&subtitleLanguage=ko&tab=community&q=1274636&category=questionDetail
}
