package br.com.springboot.app.exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4469920031191333384L;

	public UserNotFoundException(Long id) {
	    super("Could not find User " + id);
	}

}
