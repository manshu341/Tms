package com.trainer.exception;

/**
 * 
 * @author Kushal
 * @see ExperienceNotFoundException
 * @since February, 2022
 *
 */
public class ExperienceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public ExperienceNotFoundException() {
		this.msg = "Dummy not found";
	}

	public ExperienceNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ExperienceNotAvailableException " + msg;
	}

}
