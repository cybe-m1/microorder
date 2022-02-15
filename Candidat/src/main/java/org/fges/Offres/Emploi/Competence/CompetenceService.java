package org.fges.Offres.Emploi.Competence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetenceService {
	private CompetenceRepository competenceRepository;
	
	@Autowired
	public void setCompetenceRepository(CompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}
	
	public List<Competence> getAll() {
		return competenceRepository.findAll();
	}
	
	public Optional<Competence> getCompetence(int id) {
		return competenceRepository.findById(id);
	}
	
	public Set<Competence> findCompetenceByNomStartingWith(String nom) {
		return competenceRepository.findCompetenceByNomStartingWith(nom);
	}
	
	public Competence addCompetence(Competence competence) {
		return competenceRepository.save(competence);
	}
	
	public Competence updateCompetence(Competence competence) throws CompetenceNotFoundException {
		if(competenceRepository.existsById(competence.getComId())) {
			return competenceRepository.save(competence);
		}
		throw new CompetenceNotFoundException("Competence " + competence.getComId() + " n'existe pas, impossible de la modifier.");
	}
	
	public void deleteCompetence (int id) throws CompetenceNotFoundException {
		if(competenceRepository.existsById(id)) {
			competenceRepository.deleteById(id);
		} else {
			throw new CompetenceNotFoundException("Competence " + id + " n'existe pas, impossible de la supprimer.");
		}
	}
	

}
