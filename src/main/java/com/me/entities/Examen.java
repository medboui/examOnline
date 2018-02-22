package com.me.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Examen implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nomMatiere;
	private String niveauEtudes;
	@OneToMany(mappedBy="examen",fetch=FetchType.LAZY)
	private Collection<Question> questions;
	@ManyToOne
	@JoinColumn(name="CODE_Etudiant")
	private Etudiant etudiant;
	
	
	public Examen() {
		
	}
	public Examen(String nomMatiere, String niveauEtudes) {
		super();
		this.nomMatiere = nomMatiere;
		this.niveauEtudes = niveauEtudes;
	}
	public Examen(String nomMatiere, String niveauEtudes, Collection<Question> questions) {
		super();
		this.nomMatiere = nomMatiere;
		this.niveauEtudes = niveauEtudes;
		this.questions = questions;
	}
	public Examen(String nomMatiere, String niveauEtudes, Collection<Question> questions, Etudiant etudiant) {
		super();
		this.nomMatiere = nomMatiere;
		this.niveauEtudes = niveauEtudes;
		this.questions = questions;
		this.etudiant = etudiant;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomMatiere() {
		return nomMatiere;
	}
	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}
	public String getNiveauEtudes() {
		return niveauEtudes;
	}
	public void setNiveauEtudes(String niveauEtudes) {
		this.niveauEtudes = niveauEtudes;
	}
	public Collection<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

}
