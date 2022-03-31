package com.xpeer.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xpeer.demo.entity.Course;

public interface CourseService {
	
	public List<Object[]> findAll(String lang);
	
	public Page<Course> findAll(Pageable pageable,String lang);
	
	public Optional<Course> findById(Long id);
	
	
	public Course save(Course course);
	
	public void deleteById(Long id);

	public Page<Course> findByText(String strSearch,Pageable pageable);
}
