package org.fges.Offres.Emploi.Loisirs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoisirNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7421598401289246705L;

	public LoisirNotFoundException(String message) {
		super(message);
		System.err.println(message);
	}
}
