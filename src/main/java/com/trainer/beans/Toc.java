package com.trainer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Yuvraj
 * @since February, 2022
 * @see Toc Bean Class
 *
 */

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Toc {
	private int tid;
	private String cname;
	private Integer duration;
	private String audience;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	@Override
	public String toString() {
		return "Toc [tid=" + tid + ", cname=" + cname + ", duration=" + duration + ", audience=" + audience + "]";
	}
	public Toc(int tid, String cname, Integer duration, String audience) {
		super();
		this.tid = tid;
		this.cname = cname;
		this.duration = duration;
		this.audience = audience;
	}
	public Toc() {
		super();
	}
	
	
}
