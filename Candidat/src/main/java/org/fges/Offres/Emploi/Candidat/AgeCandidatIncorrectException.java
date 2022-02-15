package org.fges.Offres.Emploi.Candidat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AgeCandidatIncorrectException  extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3422771393507477214L;

	public AgeCandidatIncorrectException(String message) {
		super(message);
		System.err.println(message);
	}
}
