package org.fges.Offres.Emploi.Candidat;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.fges.Offres.Emploi.CompetenceCandidat.CompetenceCandidat;
import org.fges.Offres.Emploi.Loisirs.Loisir;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="candidat")
@Table(name="candidat")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "canId")

public class Candidat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int canId;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="date_de_naissance")
	private LocalDate dateDeNaissance;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy = "candidat")
	private Collection<CompetenceCandidat> cand_comp;
	
	@ManyToMany
	private Collection<Loisir> loisirs;

	
	
	
}
