package com.querydslJpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hello2 {
	@Id
	@GeneratedValue
	private Long id;
}
