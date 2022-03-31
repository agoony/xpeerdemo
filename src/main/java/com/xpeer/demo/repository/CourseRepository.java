package com.xpeer.demo.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xpeer.demo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	public static final String FIND_COURSES = "SELECT c.course_id,lc.city,lc.country,lc.`name`,lc.description,c.date_created,c.`status`"
			+ "FROM courses c INNER JOIN localized_course lc "
			+ "where c.course_id = lc.id and lc.locale = ?1"
			+ "";
	public static final String SEARCH_COURSES = "SELECT c.course_id,lc.city,lc.country,lc.`name`,lc.description,c.date_created,c.`status`"
			+ "FROM courses c INNER JOIN localized_course lc "
			+ "where c.course_id = lc.id and lc.locale = ?1"
			+ "";


	@Query(value = FIND_COURSES, nativeQuery = true)	
	List<Object[]> findAll( String lang);
	
	@Query("SELECT c FROM Course c WHERE c.courseName LIKE %?1%"
			+"OR c.description  LIKE %?1%")
	Page<Course> findAll(String keyword,Pageable pageable);
	


}
