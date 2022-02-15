package org.fges.Offres.Emploi.Candidat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.fges.Offres.Emploi.Competence.Competence;
import org.fges.Offres.Emploi.Competence.CompetenceRepository;
import org.fges.Offres.Emploi.Competence.NiveauExpertiseIncorrectException;
import org.fges.Offres.Emploi.CompetenceCandidat.CompetenceCandidat;
import org.fges.Offres.Emploi.CompetenceCandidat.CompetenceCandidatRepository;
import org.fges.Offres.Emploi.Loisirs.Loisir;
import org.fges.Offres.Emploi.Loisirs.LoisirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatService {
	private CandidatRepository candidatRepository;
	
	private LoisirRepository loisirRepository;
	
	private CompetenceRepository competenceRepository;
	
	private CompetenceCandidatRepository competenceCandidatRepository;
	
	@Autowired
	public void setCandidatRepository(CandidatRepository candidatRepository) {
		this.candidatRepository = candidatRepository;
	}
	@Autowired
	public void setLoisirRepository(LoisirRepository loisirRepository) {
		this.loisirRepository = loisirRepository;
	}
	@Autowired
	public void setCompetenceRepository(CompetenceRepository competenceRepository) {
		this.competenceRepository = competenceRepository;
	}
	@Autowired
	public void setCompetenceCandidatRepository(CompetenceCandidatRepository competenceCandidatRepository) {
		this.competenceCandidatRepository = competenceCandidatRepository;
	}
	
	public List<Candidat> getAll() {
		return candidatRepository.findAll();
	}
	
	public Optional<Candidat> getCandidat(int idCandidat) {
		return candidatRepository.findById(idCandidat);
	}
	
	public Candidat addCandidat(Candidat candidat) throws AgeCandidatIncorrectException {
		int age = Period.between(candidat.getDateDeNaissance(), LocalDate.now()).getYears();
		
		if(age >= 18 && age <= 67) {
			return candidatRepository.save(candidat);
		}
		
		throw new AgeCandidatIncorrectException("L'age du candidat doit être compris entre 18 et 67 ans. Il est ici de : " + age + ".");
	}
	
	public Candidat addLoisir(int idCandidat, int idLoisir) throws ErreurInsertionLoisirException {
		Candidat candidat;
		Loisir loisir;
		if(candidatRepository.existsById(idCandidat) && loisirRepository.existsById(idLoisir)) {
			candidat = candidatRepository.findById(idCandidat).get();
			loisir = loisirRepository.findById(idLoisir).get();
			
			Collection<Loisir> listLoisirs = candidat.getLoisirs();
			listLoisirs.add(loisir);
			candidat.setLoisirs(listLoisirs);
			
			candidatRepository.save(candidat);			
			return candidat;
		} else {
			throw new ErreurInsertionLoisirException("Erreur lors de l'insertion du Loisir pour le Candidat");
		}
	}
	
	public Candidat addCompetence(int idCandidat, int idCompetence, int niveauExpertise) throws ErreurInsertionCompetenceException, NiveauExpertiseIncorrectException {
		Candidat candidat;
		Competence competence;
		
		if(!(niveauExpertise > 0 && niveauExpertise <6)) {
			throw new NiveauExpertiseIncorrectException("Le niveau d'expertise doit être entre 1 et 5, ici il est de : " + niveauExpertise + ".");
		}
		
		
		if(candidatRepository.existsById(idCandidat) && competenceRepository.existsById(idCompetence)) {
			candidat = candidatRepository.findById(idCandidat).get();
			competence = competenceRepository.findById(idCompetence).get();
			
			System.out.println(candidat);
			System.out.println(competence);
		
			CompetenceCandidat comp_cand = new CompetenceCandidat();
			competenceCandidatRepository.save(comp_cand);
			
			comp_cand.setCandidat(candidat);
			comp_cand.setCompetence(competence);
			comp_cand.setNiveauExpertise(niveauExpertise);

			competenceCandidatRepository.save(comp_cand);
			candidat = candidatRepository.findById(idCandidat).get();
			
			return candidat;
			
		} else {
			throw new ErreurInsertionCompetenceException("Erreur lors de l'insertion de la Compétence pour le Candidat");
		}
		
	}
	

}
