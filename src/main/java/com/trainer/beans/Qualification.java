package com.trainer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Ashish
 * @since February, 2022
 * @see Qualification Bean
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Qualification {

	private Integer id;
	private String type;
	private Integer year;
	private String stream;
	private Double cgpa;
	private String institute;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public Double getCgpa() {
		return cgpa;
	}
	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	@Override
	public String toString() {
		return "Qualification [id=" + id + ", type=" + type + ", year=" + year + ", stream=" + stream + ", cgpa=" + cgpa
				+ ", institute=" + institute + "]";
	}
	public Qualification(Integer id, String type, Integer year, String stream, Double cgpa, String institute) {
		super();
		this.id = id;
		this.type = type;
		this.year = year;
		this.stream = stream;
		this.cgpa = cgpa;
		this.institute = institute;
	}
	public Qualification() {
		super();
	}

	
}
