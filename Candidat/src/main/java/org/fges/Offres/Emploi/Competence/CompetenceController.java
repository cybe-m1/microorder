package org.fges.Offres.Emploi.Competence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/competences")
public class CompetenceController {
	private CompetenceService competenceService;
	
	@Autowired
	public void setCompetenceService(CompetenceService competenceService) {
		this.competenceService = competenceService;
	}
	
	@GetMapping
	public List<Competence> getAll() {
		return competenceService.getAll();
	}
	
	
	@GetMapping(value="/{idCompetence}")
	public Optional<Competence> getCompetence(@PathVariable("idCompetence") int idCompetence){
		return competenceService.getCompetence(idCompetence);
	}
	
	@GetMapping(value="/searchByNom/{nom}")
	public Set<Competence> findCompetenceByNomStartingWith(@PathVariable("nom") String nom) {
		return competenceService.findCompetenceByNomStartingWith(nom);
	}
	
	@PostMapping
	public Competence save(@RequestBody Competence competence) throws NiveauExpertiseIncorrectException {
		return competenceService.addCompetence(competence);
	}
	
	@PutMapping
	public Competence update(@RequestBody Competence competence) throws CompetenceNotFoundException, NiveauExpertiseIncorrectException {
		return competenceService.updateCompetence(competence);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public Competence delete(@PathVariable int id) throws CompetenceNotFoundException {
		Competence toDelete = competenceService.getCompetence(id).get();
		competenceService.deleteCompetence(id);
		return toDelete;
	}
	
}
