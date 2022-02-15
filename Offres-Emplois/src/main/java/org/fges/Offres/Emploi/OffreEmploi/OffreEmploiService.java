package org.fges.Offres.Emploi.OffreEmploi;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.fges.Offres.Emploi.Candidat.Candidat;
import org.fges.Offres.Emploi.Candidat.CandidatRepository;
import org.fges.Offres.Emploi.Candidat.ErreurInsertionCompetenceException;
import org.fges.Offres.Emploi.Competence.Competence;
import org.fges.Offres.Emploi.Competence.CompetenceRepository;
import org.fges.Offres.Emploi.CompetenceOffreEmploi.CompetenceOffreEmploi;
import org.fges.Offres.Emploi.CompetenceOffreEmploi.CompetenceOffreEmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffreEmploiService {
	private OffreEmploiRepository offreEmploiRepository;
	
	private CompetenceRepository competenceRepository;
	
	private CompetenceOffreEmploiRepository competenceOffreEmploiRepository;
	
	@Autowired
	public void setOffreEmploiRepository(OffreEmploiRepository offreEmploiRepository) {
		this.offreEmploiRepository = offreEmploiRepository;
	}
	@Autowired
	public void setCompetenceRepository(CompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}
	@Autowired
	public void setCompetenceOffreEmploiRepository(CompetenceOffreEmploiRepository competenceOffreEmploiRepository) {
		this.competenceOffreEmploiRepository = competenceOffreEmploiRepository;
	}
	
	public List<OffreEmploi> getAll() {
		return offreEmploiRepository.findAll();
	}
	
	public Optional<OffreEmploi> getOffreEmploi(int idOffreEmploi) {
		return offreEmploiRepository.findById(idOffreEmploi);
	}
	
	public OffreEmploi addOffreEmploi(OffreEmploi offreEmploi) {
		return offreEmploiRepository.save(offreEmploi);
	}
	
	public Set<OffreEmploi> findOffreEmploiByNomStartingWith(String nom) {
		return offreEmploiRepository.findOffreEmploiByNomStartingWith(nom);
	}
	
	public OffreEmploi updateOffreEmploi(OffreEmploi offreEmploi) throws OffreEmploiNotFoundException {
		if(offreEmploiRepository.existsById(offreEmploi.getOffId())) {
			return offreEmploiRepository.save(offreEmploi);
		}
		throw new OffreEmploiNotFoundException("Offre d'emploi " + offreEmploi.getOffId() + " n'existe pas, impossible de le modifier.");
	}
	
	public void deleteOffreEmploi(int id) throws OffreEmploiNotFoundException {
		if(offreEmploiRepository.existsById(id)) {
			OffreEmploi toDisable = getOffreEmploi(id).get();
			toDisable.setActif(false);
			offreEmploiRepository.save(toDisable);
		} else {
			throw new OffreEmploiNotFoundException("Offre d'emploi " + id + " n'existe pas, impossible de la supprimer.");
		}
		
	}

	public OffreEmploi addCompetence(int idOffreEmploi, int idCompetence, boolean obligatoire) throws Exception {
		OffreEmploi offreEmploi;
		Competence competence;
		
		if(offreEmploiRepository.existsById(idOffreEmploi) && competenceRepository.existsById(idCompetence)) {
			offreEmploi = offreEmploiRepository.findById(idOffreEmploi).get();
			competence = competenceRepository.findById(idCompetence).get();
			
			CompetenceOffreEmploi comp_off = new CompetenceOffreEmploi();
			competenceOffreEmploiRepository.save(comp_off);
			
			comp_off.setOffreEmploi2(offreEmploi);
			comp_off.setCompetence2(competence);
			comp_off.setObligatoire(obligatoire);
			
			competenceOffreEmploiRepository.save(comp_off);
			
			offreEmploi = offreEmploiRepository.findById(idOffreEmploi).get();
			
			return offreEmploi;
		}
		
		throw new Exception("Erreur lors de l'insertion de la Competence pour l'Offre d'Emploi");
		
	}
}
