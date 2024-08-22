package com.synex.domain;

public class TaskDto {
	
		private String id;
	
	private String title;
	private String description;
	private TaskStatus status;
	
	public TaskDto(String id, String title, String description, TaskStatus status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	TaskDto(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	

}
