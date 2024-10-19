package hellojpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	private String createdBy;
	private String lastModifiedBy;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
}
