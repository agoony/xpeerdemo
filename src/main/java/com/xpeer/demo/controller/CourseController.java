package com.xpeer.demo.controller;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.xpeer.demo.entity.Course;
import com.xpeer.demo.entity.LocalizedCourse;
import com.xpeer.demo.entity.LocalizedId;
import com.xpeer.demo.entity.Status;
import com.xpeer.demo.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController { 

	@Autowired
	private CourseService  courseService;
	
	private final static List<String> ACEPTED_LOCALES = Arrays.asList("es","en","fr");
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Course course, @RequestParam(value = "lang")String lang){
						
		Date date = new Date();
		course.setPublicationDate(date);
		
		if(lang != null ) {
			LocalizedCourse localizedCourse =  new LocalizedCourse();
			localizedCourse.setCity(course.getCity());
			localizedCourse.setCountry(course.getCountry());
			localizedCourse.setDescription(course.getDescription());
			localizedCourse.setName(course.getCourseName());
			localizedCourse.setLocalizedId(new LocalizedId(lang));	
			localizedCourse.setCourse(course);
			course.getLocalizations().put(lang, localizedCourse);
			
			};
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long id,
								  @RequestParam(value = "lang")String lang)
	{
		Optional<Course> oCourse = courseService.findById(id);
		if(!oCourse.isPresent()) {
			return ResponseEntity.notFound().build();
		}	
		//return only the selected languages 
		Course course = oCourse.get();
		if(lang != null && course.getLocalizations().containsKey(lang)) {
			LocalizedCourse localizedCourse = course.getLocalizations().get(lang);
			course.setCity(localizedCourse.getCity());
			course.setCountry(localizedCourse.getCountry());
			course.setDescription(localizedCourse.getDescription());
			course.setCourseName(localizedCourse.getName());
			
			};
			course.getLocalizations().clear();
		
		
		return ResponseEntity.ok(course);
		
	}
	
	//Update Course 
	@PutMapping("/{id}")
	public  ResponseEntity<?> update (@RequestBody Course courseDetails,@PathVariable(value = "id" )Long courseId,
									  @RequestParam(value = "lang")String lang)
	{
		Optional<Course> course = courseService.findById(courseId);
		if(!course.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		if(lang != null && course.get().getLocalizations().containsKey(lang)) {
			LocalizedCourse localizedCourse = course.get().getLocalizations().get(lang);
			localizedCourse.setCity(courseDetails.getCity());
			localizedCourse.setCountry(courseDetails.getCountry());
			localizedCourse.setDescription(courseDetails.getDescription());
			localizedCourse.setName(courseDetails.getCourseName());
			localizedCourse.setCourse(course.get());
			course.get().getLocalizations().put(lang, localizedCourse);
			
		} else {
			LocalizedCourse localizedCourse = new LocalizedCourse();
			localizedCourse.setCity(courseDetails.getCity());
			localizedCourse.setCountry(courseDetails.getCountry());
			localizedCourse.setDescription(courseDetails.getDescription());
			localizedCourse.setName(courseDetails.getCourseName());
			localizedCourse.setLocalizedId(new LocalizedId(lang));	
			localizedCourse.setCourse(course.get());
			course.get().getLocalizations().put(lang, localizedCourse);
			}
		
		course.get().setStatus(courseDetails.getStatus());
		
		Course savedCourse = courseService.save(course.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
		}
	
	
	//Delete Course
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable( value="id") Long courseId) {
		Optional<Course> course = courseService.findById(courseId);
		if(!course.isPresent()) {
			return ResponseEntity.notFound().build();					
		}
		
		courseService.deleteById(courseId);
		return  ResponseEntity.ok().build();
	}
	
	//Read All Courses

	@GetMapping
	public List<Course> readAll(@RequestParam(value = "lang")String lang){
		
		List<Object[]> objectList = new ArrayList<Object[]>();
		objectList = courseService.findAll(lang);
		    List<Course> courses = new ArrayList<>();
		    for (Object[] tuple : objectList) {
		    	Long col1 = ((java.math.BigInteger)tuple[0]).longValue();
		        String col2 = (String) tuple[1];
		        String col3 = (String) tuple[2];
		        String col4 = (String) tuple[3];
		        String col5 = (String) tuple[4];
		        Date col6 = (Date) tuple[5];
		        Status col7 = Status.valueOf((String) tuple[6]);

		        Course obj = new Course();
		        obj.setCourseId(col1);
		        obj.setCity(col2);
		        obj.setCountry(col3);
		        obj.setCourseName(col4);
		        obj.setDescription(col5);
		        obj.setPublicationDate(col6);
		        obj.setStatus(col7);
		        
		        courses.add(obj);
		    }
		
	
		
		return courses;
		
	}

	 
	@GetMapping("search/{text}/{page}")
		public  ResponseEntity<?> search (@PathVariable(value = "text" )String strSearch,@PathVariable(value = "page" )int page)
		{
			//TO-DO: add page reading by parameter
			Pageable firstPageWithTwoElements = PageRequest.of(page, 2);//force 2 results per page
			Page<Course> course = courseService.findByText(strSearch,firstPageWithTwoElements);
						
			return ResponseEntity.status(HttpStatus.CREATED).body(course.get());
			}
	
	
}
