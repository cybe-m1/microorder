package org.fges.Offres.Emploi.Loisirs;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoisirRepository extends JpaRepository<Loisir, Integer> {
	Set<Loisir> findLoisirByNomStartingWith(String nom);
	
}
