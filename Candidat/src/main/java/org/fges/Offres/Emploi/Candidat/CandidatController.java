package org.fges.Offres.Emploi.Candidat;

import java.util.List;
import java.util.Optional;

import org.fges.Offres.Emploi.Competence.NiveauExpertiseIncorrectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/candidats")
public class CandidatController {

	private CandidatService candidatService;
	
	@Autowired
	public void setCandidatService(CandidatService candidatService) {
		this.candidatService = candidatService;
	}
	
	@GetMapping
	public List<Candidat> getAll() {
		return candidatService.getAll();
	}
	
	@GetMapping(value="/{idCandidat}")
	public Optional<Candidat> getCandidat(@PathVariable("idCandidat") int idCandidat) {
		return candidatService.getCandidat(idCandidat);
	}
	
	@PostMapping
	public Candidat save(@RequestBody Candidat candidat) throws AgeCandidatIncorrectException {
		return candidatService.addCandidat(candidat);
	}
	
	@PostMapping(value="/{idCandidat}/addLoisir/{idLoisir}")
	public Candidat addLoisir(@PathVariable(value="idCandidat") int idCandidat, @PathVariable(value="idLoisir") int idLoisir) throws ErreurInsertionLoisirException {
		return candidatService.addLoisir(idCandidat, idLoisir);
	}
	
	@PostMapping(value="/{idCandidat}/addCompetence/{idCompetence}")
	public void addCompetence(@PathVariable(value="idCandidat") int idCandidat, @PathVariable(value="idCompetence") int idCompetence, @RequestParam(value="niveauExpertise") int niveauExpertise) throws ErreurInsertionCompetenceException, NiveauExpertiseIncorrectException {
		System.out.println("Id du candidat " + idCandidat);
		System.out.println("Id de la competence " + idCompetence);
		System.out.println("Niveau d'expertise " + niveauExpertise);
		
		candidatService.addCompetence(idCandidat, idCompetence, niveauExpertise);
	}
	
}
