package com.synex.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Task;
import com.synex.domain.TaskDto;
import com.synex.domain.TaskStatus;
import com.synex.repository.TaskRepository;

@RestController
public class TaskController {
	
	private final TaskRepository repository;
	
	public TaskController(TaskRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<Long> createTask(@RequestBody TaskDto taskDto){
		Task newTask = new Task(taskDto.getTitle());
		newTask.setDescription(taskDto.getDescription());
		Task savedTask = repository.save(newTask);
		return new ResponseEntity<>(savedTask.getId(), HttpStatus.OK);
	}
	
	@GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return new ResponseEntity<>(task.get().toDto(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	
	@PutMapping("/task/{id}")
	public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
	    Optional<Task> taskOptional = repository.findById(id);
	    if (!taskOptional.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    Task task = taskOptional.get();
	    if (taskDto.getTitle() != null) {
	        task.setTitle(taskDto.getTitle());
	    }
	    if (taskDto.getDescription() != null) {
	        task.setDescription(taskDto.getDescription());
	    }
	    if (taskDto.getStatus() != null) {
	        try {
	        	task.setStatus(taskDto.getStatus());
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>("Invalid status provided. Available statuses are: CREATED, APPROVED, REJECTED, BLOCKED, DONE.", HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    repository.save(task);
	    return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
	}

	
	/*@PutMapping("/task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Optional<Task> taskOptional = repository.findById(id);
        if (!taskOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        Task task = taskOptional.get();
        if (taskDto.getTitle() != null) {
            task.setTitle(taskDto.getTitle());
        }
        if (taskDto.getDescription() != null) {
            task.setDescription(taskDto.getDescription());
        }
        if (taskDto.getStatus() != null) {
            try {
                TaskStatus status = TaskStatus.valueOf(taskDto.getStatus());
            	
                task.setStatus(status);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("Available statuses are: CREATED, APPROVED, REJECTED, BLOCKED, DONE.", HttpStatus.OK);
            }
        }
        repository.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
	
	
	@DeleteMapping("/task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/task/describe/{id}")
    public ResponseEntity<String> describeTask(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            Task t = task.get();
            String description = String.format("Description of Task [%d: %s] is: %s", t.getId(), t.getTitle(), t.getDescription());
            return new ResponseEntity<>(description, HttpStatus.OK);
        }
        return new ResponseEntity<>("Task with id = " + id + " does not exist", HttpStatus.OK);
    }
	
	@GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = ((List<Task>) repository.findAll()).stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
	
	@GetMapping("/describe")
    public ResponseEntity<List<String>> describeAllTasks() {
        List<String> descriptions = ((List<Task>) repository.findAll()).stream()
                .map(t -> String.format("Description of Task [%d: %s] is: %s", t.getId(), t.getTitle(), t.getDescription()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(descriptions, HttpStatus.OK);
    }

}
