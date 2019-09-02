package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.task.TaskForm;
import com.example.demo.entity.Task;

public interface TaskService {

	List<Task> findAll();
	
	Optional<Task> getTask(int id);

	void insert(Task task);
	
	void update(Task task);
	
	void deleteById(int id);

}
