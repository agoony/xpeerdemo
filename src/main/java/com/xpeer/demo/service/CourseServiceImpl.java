package com.xpeer.demo.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpeer.demo.entity.Course;
import com.xpeer.demo.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Transactional(readOnly = true)
	public List<Object[]> findAll(String lang) {
		return courseRepository.findAll(lang);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findById(Long id) {		
		return courseRepository.findById(id);
	}

	@Override
	@Transactional
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		courseRepository.deleteById(id);		
	}

	@Override
	public Page<Course> findByText(String strSearch,Pageable pageable) {
		
	  
		return  courseRepository.findAll(strSearch, pageable);
	}

	@Override
	public Page<Course> findAll(Pageable pageable, String lang) {
		
		 return  courseRepository.findAll( pageable);
	}

	

	

}
