package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	public Task(String title) {
		this.title = title;
		this.status = TaskStatus.CREATED;
	}
	
	public Task () {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public TaskDto toDto() {
		
	return new TaskDto(String.valueOf(id), title, description, status) ;
	
		
	}
	
	

}
