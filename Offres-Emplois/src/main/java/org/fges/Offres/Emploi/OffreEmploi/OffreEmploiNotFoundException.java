package org.fges.Offres.Emploi.OffreEmploi;

public class OffreEmploiNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2945871196060033202L;

	public OffreEmploiNotFoundException(String message) {
		super(message);
		System.err.println(message);
	}

}
