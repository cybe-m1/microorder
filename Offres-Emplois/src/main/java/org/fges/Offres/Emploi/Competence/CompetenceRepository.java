package org.fges.Offres.Emploi.Competence;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Integer>{
	Set<Competence> findCompetenceByNomStartingWith(String nom);

}
