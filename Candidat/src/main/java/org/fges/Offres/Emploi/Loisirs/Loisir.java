package org.fges.Offres.Emploi.Loisirs;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.fges.Offres.Emploi.Candidat.Candidat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="loisir")
@Table(name="loisir")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "loiId")
public class Loisir {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loiId;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="descriptif")
	private String descriptif;
	
	@ManyToMany(mappedBy = "loisirs", fetch = FetchType.EAGER)
	@JsonIgnore				//fetchtype lazy
	private Collection<Candidat> candidat; //candidats
	//json ignore
	
	//autre méthode : json view COMPLEXE
	

}
