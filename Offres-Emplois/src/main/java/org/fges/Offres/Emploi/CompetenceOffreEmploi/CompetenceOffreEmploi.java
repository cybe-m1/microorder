package org.fges.Offres.Emploi.CompetenceOffreEmploi;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fges.Offres.Emploi.Competence.Competence;
import org.fges.Offres.Emploi.OffreEmploi.OffreEmploi;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="competence_offreemploi")
@Table(name="competence_offreemploi")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CompetenceOffreEmploi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int coId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Competence competence2;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private OffreEmploi offreEmploi2;
	
	@Column (name="obligatoire")
	private boolean obligatoire;
	
	

}
