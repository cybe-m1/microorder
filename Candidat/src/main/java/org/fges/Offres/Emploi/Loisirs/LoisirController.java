package org.fges.Offres.Emploi.Loisirs;

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
@RequestMapping(path = "/loisirs")
public class LoisirController {
	
	private LoisirService loisirService;
	
	@Autowired
	public void setLoisirService(LoisirService loisirService) {
		this.loisirService = loisirService;
	}
	
	@GetMapping
	public List<Loisir> getAll() {
		return loisirService.getAll();
	}
	
	@GetMapping(value="/{idLoisir}")
	public Optional<Loisir> getLoisir(@PathVariable("idLoisir") int idLoisir) {
		return loisirService.getLoisir(idLoisir);
	}
	
	@GetMapping(value="/searchByNom/{nom}")
	public Set<Loisir> findLoisirByNomStartingWith(@PathVariable("nom") String nom) {
		return loisirService.findLoisirByNomStartingWith(nom);
	}
	
	@PostMapping
	public Loisir save(@RequestBody Loisir loisir) {
		return loisirService.addLoisir(loisir);
	}
	
	@PutMapping
	public Loisir update(@RequestBody Loisir loisir) throws LoisirNotFoundException {
		return loisirService.updateLoisir(loisir);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public Loisir delete(@PathVariable int id) throws LoisirNotFoundException {
		Loisir toDelete = loisirService.getLoisir(id).get();
		loisirService.deleteLoisir(id);
		return toDelete;
	}
	

}
