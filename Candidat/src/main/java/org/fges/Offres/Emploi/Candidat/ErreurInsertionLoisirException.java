package org.fges.Offres.Emploi.Candidat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErreurInsertionLoisirException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6760568837791513045L;

	public ErreurInsertionLoisirException(String message) {
		super(message);
		System.err.println(message);
	}

}
