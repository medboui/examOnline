package com.me.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private boolean res;
	@ManyToOne
	@JoinColumn(name="CODE_EXAMEN")
	private Examen examen;
	@ManyToOne
	@JoinColumn(name="CODE_ETUDIANT")
	private Etudiant etudiant;
	
	@OneToOne
	@JoinColumn(name="CODE_QUESTION")
	private Question question2;
	
	public Response() {
		super();
	}
	public Response(String description,boolean res, Question question2) {
		this.description = description;
		this.res = res;
		this.question2 = question2;
	}
	public Response(String description, boolean res, Examen examen,Etudiant etudiant, Question question2) {
		super();
		this.description = description;
		this.res = res;
		this.examen = examen;
		this.etudiant=etudiant;
		this.question2 = question2;
	}
	public Response(String description, boolean res, Examen examen, Question question2) {
		super();
		this.description = description;
		this.res = res;
		this.examen = examen;
		this.question2 = question2;
	}
	public boolean getRes() {
		return res;
	}
	public void setRes(boolean res) {
		this.res = res;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	public Question getQuestion() {
		return question2;
	}
	public void setQuestion(Question question) {
		this.question2 = question;
	}
	public Response(boolean res, Question question2) {
		super();
		this.res = res;
		this.question2 = question2;
	}

}
