package com.trainer.exception;

/**
 * 
 * @author Satyaa
 * @see TrainerNotFoundException
 * @since February, 2022
 *
 */
public class TrainerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public TrainerNotFoundException() {
		this.msg = "User not found";
	}

	public TrainerNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "TrainerNotFoundException " + msg;
	}

}
