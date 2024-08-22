package com.synex.repository;

import org.springframework.data.repository.CrudRepository;

import com.synex.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{

}
