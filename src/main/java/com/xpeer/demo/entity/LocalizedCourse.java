package com.xpeer.demo.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class LocalizedCourse {
	@EmbeddedId
    private LocalizedId localizedId;
	
	@JsonIgnore
    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Course course;
     
    private String name;
     
    private String description;
    
    private String city;
    
    private String country;

	public LocalizedId getLocalizedId() {
		return localizedId;
	}

	public void setLocalizedId(LocalizedId localizedId) {
		this.localizedId = localizedId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}

