package com.trainer.exception;

/**
 * 
 * @author Ashish
 * @see QualificationNotFoundException
 * @since February, 2022
 *
 */

public class QualificationNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public String msg;

	public QualificationNotFoundException() {
		this.msg = "Qualification not found";

	}

	public QualificationNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "QualificationNotFoundException [msg=" + msg + "]";
	}

}
