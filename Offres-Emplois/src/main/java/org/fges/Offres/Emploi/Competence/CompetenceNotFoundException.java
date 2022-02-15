package org.fges.Offres.Emploi.Competence;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompetenceNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1920383115908924516L;

	public CompetenceNotFoundException(String message) {
		super(message);
		System.err.println(message);
	}
	

}
