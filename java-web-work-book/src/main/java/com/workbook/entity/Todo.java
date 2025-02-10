package com.workbook.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"tno", "title", "dueDate", "finished"})
@Builder
public class Todo {
	@Id
	@GeneratedValue
	@Column(name = "tno")
	private Long tno;

	@Column(nullable = false, length = 100)
	private String title;

	private LocalDate dueDate;
	private LocalDateTime finished;
	
	private String writer;

}
