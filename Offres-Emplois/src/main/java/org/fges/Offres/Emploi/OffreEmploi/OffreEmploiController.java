package org.fges.Offres.Emploi.OffreEmploi;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.fges.Offres.Emploi.Candidat.Candidat;
import org.fges.Offres.Emploi.Candidat.ErreurInsertionCompetenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/offresEmplois")
public class OffreEmploiController {
	
	private OffreEmploiService offreEmploiService;
	
	@Autowired
	public void setOffreEmploiService(OffreEmploiService offreEmploiService) {
		this.offreEmploiService = offreEmploiService;
	}
	
	@GetMapping
	public List<OffreEmploi> getAll() {
		return offreEmploiService.getAll();
	}
	
	@GetMapping(value="/{idOffreEmploi}")
	public Optional<OffreEmploi> getOffreEmploi(@PathVariable("idOffreEmploi") int idOffreEmploi) {
		return offreEmploiService.getOffreEmploi(idOffreEmploi);
	}
	
	@GetMapping(value="/searchByNom/{nom}")
	public Set<OffreEmploi> findOffreEmploisByNomStartingWith(@PathVariable("nom") String nom) {
		return offreEmploiService.findOffreEmploiByNomStartingWith(nom);
	}
	
	@PostMapping
	public OffreEmploi save(@RequestBody OffreEmploi offreEmploi) {
		return offreEmploiService.addOffreEmploi(offreEmploi);
	}
	
	@PutMapping
	public OffreEmploi update(@RequestBody OffreEmploi offreEmploi) throws OffreEmploiNotFoundException {
		return offreEmploiService.updateOffreEmploi(offreEmploi);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public OffreEmploi delete(@PathVariable int id) throws OffreEmploiNotFoundException{
		OffreEmploi toDelete = offreEmploiService.getOffreEmploi(id).get();
		offreEmploiService.deleteOffreEmploi(id);
		return toDelete;
		
	}
	
	@PostMapping(value="/{idOffreEmploi}/addCompetence/{idCompetence}")
	public OffreEmploi addCompetence(@PathVariable(value="idOffreEmploi") int idOffreEmploi, @PathVariable(value="idCompetence") int idCompetence, @RequestParam(value="obligatoire") boolean obligatoire) throws Exception {
		
		return offreEmploiService.addCompetence(idOffreEmploi, idCompetence, obligatoire);
	}
	
	@PostMapping(value="/{idOffreEmploi}/postuler/{idCandidat}")
	public OffreEmploi postuler(@PathVariable(value="idOffreEmploi") int idOffreEmploi, @PathVariable(value="idCandidat") int idCandidat) throws OffreEmploiInactiveException, ErreurInsertionCandidatException {
		
		return offreEmploiService.addCandidat(idOffreEmploi, idCandidat);
	}
}
