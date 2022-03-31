package com.xpeer.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Courses")
public class Course implements Serializable {

	
	private static final long serialVersionUID = 4133654964736366900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	@Column(length = 250,nullable = false)
	private String courseName; // (mandatory)
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String city;

	@Column(length = 10000)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('DRAFT', 'PUBLISHED', 'ARCHIVED')")
	private Status status; // (DRAFT, PUBLISHED, ARCHIVED)

	
	@Column(name = "DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publicationDate; // (mandatory)

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Module.class)
	@JoinColumn(name = "from_course_id")
	private List<Module> moduleList;
	
	@JsonIgnore
	   @OneToMany(mappedBy = "course", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
	    @MapKey(name = "localizedId.locale")
	    @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	    private Map<String, LocalizedCourse> localizations = new HashMap<>();
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public Map<String, LocalizedCourse> getLocalizations() {
		return localizations;
	}

	public void setLocalizations(Map<String, LocalizedCourse> localizations) {
		this.localizations = localizations;
	}

	public void setStatus(String col7) {
		// TODO Auto-generated method stub
		
	}

}
