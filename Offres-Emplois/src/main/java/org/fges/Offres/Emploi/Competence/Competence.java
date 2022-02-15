package org.fges.Offres.Emploi.Competence;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.fges.Offres.Emploi.CompetenceOffreEmploi.CompetenceOffreEmploi;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="competence")
@Table(name="competence")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "comId")
public class Competence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comId;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="descriptif")
	private String descriptif;
	
	@OneToMany(mappedBy="competence2")
	@JsonIgnore
	private Collection<CompetenceOffreEmploi> comp_offr;
	

}
