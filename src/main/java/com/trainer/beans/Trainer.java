package com.trainer.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Satyaa
 * @since February, 2022
 * @see Trainer Beans
 */
//@Data
//@AllArgsConstructor
public class Trainer {

	private Integer id;
	private String name;
	private String password;
	private String email;
	private long phone;

	private List<Qualification> qualificationList;
	private List<Toc> toc;
	private List<Experience> experienceList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public List<Qualification> getQualificationList() {
		return qualificationList;
	}
	public void setQualificationList(List<Qualification> qualificationList) {
		this.qualificationList = qualificationList;
	}
	public List<Toc> getToc() {
		return toc;
	}
	public void setToc(List<Toc> toc) {
		this.toc = toc;
	}
	public List<Experience> getExperienceList() {
		return experienceList;
	}
	public void setExperienceList(List<Experience> experienceList) {
		this.experienceList = experienceList;
	}
	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", qualificationList=" + qualificationList + ", toc=" + toc + ", experienceList="
				+ experienceList + "]";
	}
	public Trainer(Integer id, String name, String password, String email, long phone,
			List<Qualification> qualificationList, List<Toc> toc, List<Experience> experienceList) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.qualificationList = qualificationList;
		this.toc = toc;
		this.experienceList = experienceList;
	}
	public Trainer() {
		super();
	}
	
	

}
