package org.fges.Offres.Emploi.OffreEmploi;

public class OffreEmploiInactiveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6908658820949220014L;

	public OffreEmploiInactiveException(String message) {
		super(message);
		System.err.println(message);
	}
	
}
