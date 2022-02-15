package org.fges.Offres.Emploi.OffreEmploi;

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

import org.fges.Offres.Emploi.Candidat.Candidat;
import org.fges.Offres.Emploi.CompetenceOffreEmploi.CompetenceOffreEmploi;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="offreEmploi")
@Table(name="offreEmploi")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "offId")
public class OffreEmploi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int offId;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="descriptif")
	private String descriptif;
	
	@Column(name="date_dispo")
	private LocalDate dateDispo;
	
	@Column(name="actif")
	private boolean actif;
	
	@OneToMany(mappedBy = "offreEmploi2")
	private Collection<CompetenceOffreEmploi> off_comp;
}
