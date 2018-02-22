package com.me.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;

@Entity
//@Scope("session")
public class Etudiant{
	
	@Id
	@GeneratedValue()
	private Long id;
	private String nom;
	private String prenom;
	private int niveauEtudes;
	private String login;
	private String passWord;
	@OneToMany
	@JoinColumn(name="etudiant")
	private Collection<Examen> examens;
	
	public Etudiant() {
		super();
	}
	
	
	public Etudiant(String nom,String prenom, int niveauEtudes, String login, String passWord) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.niveauEtudes = niveauEtudes;
		this.login = login;
		this.passWord = passWord;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNiveauEtudes() {
		return niveauEtudes;
	}

	public void setNiveauEtudes(int niveauEtudes) {
		this.niveauEtudes = niveauEtudes;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Collection<Examen> getExamens() {
		return examens;
	}

	public void setExamens(Collection<Examen> examens) {
		this.examens = examens;
	}

	


	
	
	
}
