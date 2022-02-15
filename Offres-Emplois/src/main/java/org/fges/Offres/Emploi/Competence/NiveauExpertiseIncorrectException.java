package org.fges.Offres.Emploi.Competence;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NiveauExpertiseIncorrectException extends Exception{
	
	public NiveauExpertiseIncorrectException(String message) {
		super(message);
		System.err.println(message);
	}
	

}
