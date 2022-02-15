package org.fges.Offres.Emploi.CompetenceCandidat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fges.Offres.Emploi.Candidat.Candidat;
import org.fges.Offres.Emploi.Competence.Competence;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="competence_candidat")
@Table(name="competence_candidat")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ccId")
public class CompetenceCandidat {
	
	public CompetenceCandidat(Competence competence2, Candidat candidat2, int niveauExpertise2) {
		this.competence = competence2;
		this.candidat = candidat2;
		this.niveauExpertise = niveauExpertise2;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ccId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Competence competence;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Candidat candidat;
	
	@Column(name="niveau_expertise")
	private int niveauExpertise;

}
