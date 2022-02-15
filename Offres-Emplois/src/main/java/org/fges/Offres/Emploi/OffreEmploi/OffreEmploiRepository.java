package org.fges.Offres.Emploi.OffreEmploi;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, Integer>{
	Set<OffreEmploi> findOffreEmploiByNomStartingWith(String nom);
}
