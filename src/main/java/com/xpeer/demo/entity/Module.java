package com.xpeer.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Modules")
public class Module {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moduleId;
	
	@Column(name = "to_course_id")
	private Long toCourseId;
	
	@Column(length = 250,nullable = false)
	private String moduleName; // (mandatory)

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getToCourseId() {
		return toCourseId;
	}

	public void setToCourseId(Long toCourseId) {
		this.toCourseId = toCourseId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	
}
