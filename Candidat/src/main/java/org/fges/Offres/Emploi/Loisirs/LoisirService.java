package org.fges.Offres.Emploi.Loisirs;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoisirService {
	private LoisirRepository loisirRepository;
	
	@Autowired
	public void setLoisirRepository(LoisirRepository loisirRepository) {
		this.loisirRepository = loisirRepository;
	}
	
	public List<Loisir> getAll() {
		return loisirRepository.findAll();
	}
	
	public Optional<Loisir> getLoisir(int idLoisir) {
		return loisirRepository.findById(idLoisir);
	}
	
	public Loisir addLoisir(Loisir loisir) {
		return loisirRepository.save(loisir);
	}
	
	public Set<Loisir> findLoisirByNomStartingWith(String nom) {
		return loisirRepository.findLoisirByNomStartingWith(nom);
	}
	
	public Loisir updateLoisir(Loisir loisir) throws LoisirNotFoundException {
		if(loisirRepository.existsById(loisir.getLoiId())) {
			return loisirRepository.save(loisir);
		}
		
		throw new LoisirNotFoundException("Loisir " + loisir.getLoiId() + " n'existe pas, impossible de le modifier.");
	}
	
	public void deleteLoisir(int id) throws LoisirNotFoundException {
		if(loisirRepository.existsById(id)) {
			loisirRepository.deleteById(id);
		} else {
			throw new LoisirNotFoundException("Loisir " + id + " n'existe pas, impossible de le supprimer.");
		}
		
		
	}
	
	
	

}
