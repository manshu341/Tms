
package com.trainer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Kushal
 * @since February,2022
 * @see trainer experience bean
 *
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Experience {

	private Integer id;
	private String companyName;
	private Integer duration;
	private String technology;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	@Override
	public String toString() {
		return "Experience [id=" + id + ", companyName=" + companyName + ", duration=" + duration + ", technology="
				+ technology + "]";
	}
	public Experience(Integer id, String companyName, Integer duration, String technology) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.duration = duration;
		this.technology = technology;
	}
	public Experience() {
		super();
	}
	
	

}
