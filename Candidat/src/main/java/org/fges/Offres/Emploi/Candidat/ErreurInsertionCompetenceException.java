package org.fges.Offres.Emploi.Candidat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErreurInsertionCompetenceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3302618101369064148L;
	
	public ErreurInsertionCompetenceException(String message) {
		super(message);
		System.err.println(message);
	}

}
