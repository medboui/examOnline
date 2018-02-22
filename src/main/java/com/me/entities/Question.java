package com.me.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	private int NumQuestion;
	 
	@Column(name = "question")
	private String enonce;
	private String choix1;
	private String choix2;
	private String choix3;
	private String bonChoix;
	@ManyToOne
	@JoinColumn(name="CODE_EXAMEN")
	private Examen examen;
	@OneToOne
	@JoinColumn(name="CODE_RESPONSE")
	private Question response;
	
	
	public Question() {
	
	}
	public Question(int numQuestion, String enonce, String choix1, String choix2, String choix3, String bonChoix,
			Examen examen) {
		super();
		NumQuestion = numQuestion;
		this.enonce = enonce;
		this.choix1 = choix1;
		this.choix2 = choix2;
		this.choix3 = choix3;
		this.bonChoix = bonChoix;
		this.examen = examen;
	}


	public int getNumQuestion() {
		return NumQuestion;
	}


	public void setNumQuestion(int numQuestion) {
		NumQuestion = numQuestion;
	}


	public Question getResponse() {
		return response;
	}


	public void setResponse(Question response) {
		this.response = response;
	}
	
	
	public Question(String enonce, String choix1, String choix2, String choix3, String bonChoix) {
		super();
		this.enonce = enonce;
		this.choix1 = choix1;
		this.choix2 = choix2;
		this.choix3 = choix3;
		this.bonChoix = bonChoix;
	}


	public String getEnonce() {
		return enonce;
	}


	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}


	public Question(String enonce, String choix1, String choix2, String choix3, String bonChoix, Examen examen) {
		super();
		this.enonce = enonce;
		this.choix1 = choix1;
		this.choix2 = choix2;
		this.choix3 = choix3;
		this.bonChoix = bonChoix;
		this.examen = examen;
	}


	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return enonce;
	}

	public void setQuestion(String question) {
		this.enonce = question;
	}

	public String getChoix1() {
		return choix1;
	}

	public void setChoix1(String choix1) {
		this.choix1 = choix1;
	}

	public String getChoix2() {
		return choix2;
	}

	public void setChoix2(String choix2) {
		this.choix2 = choix2;
	}

	public String getChoix3() {
		return choix3;
	}

	public void setChoix3(String choix3) {
		this.choix3 = choix3;
	}

	public String getBonChoix() {
		return bonChoix;
	}
	public void setBonChoix(String bonChoix) {
		this.bonChoix = bonChoix;
	}
	
}
