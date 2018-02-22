package com.me.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Professeur {
	@Id
	@GeneratedValue()
	private Long id;
	private String nom;
	private String prenom;
	private String Login;
	private String PassWord;
	String matiere;
	@OneToMany
	@JoinColumn(name="professeur")
	private Collection<Examen> examens;
	
	public Professeur(String nom,String prenom, String login, String passWord, String matiere) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		Login = login;
		PassWord = passWord;
		this.matiere = matiere;
	}

	
	
	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public Professeur() {
		super();
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public Collection<Examen> getExamens() {
		return examens;
	}

	public void setExamens(Collection<Examen> examens) {
		this.examens = examens;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	
}
