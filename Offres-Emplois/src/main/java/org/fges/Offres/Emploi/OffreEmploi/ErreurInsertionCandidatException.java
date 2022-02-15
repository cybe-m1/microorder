package org.fges.Offres.Emploi.OffreEmploi;

public class ErreurInsertionCandidatException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3789499426023850209L;

	public ErreurInsertionCandidatException(String message) {
		super(message);
		System.err.println(message);
	}

}
