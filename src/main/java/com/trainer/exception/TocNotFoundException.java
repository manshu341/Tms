package com.trainer.exception;

/**
 * @author Yuvraj
 * @since February, 2022
 * @see TocNotFoundException
 *
 */
public class TocNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public TocNotFoundException() {
		this.msg = "Toc not found";
	}

	public TocNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "TocNotFoundException " + msg;
	}

}
