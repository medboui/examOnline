package com.me.metier;

import java.util.List;

import org.springframework.data.domain.Page;

import com.me.entities.Examen;
import com.me.entities.Question;

public interface ISchoolMetier {

	public Examen consulterExamen(Long idExamen);

	public Page<Examen> listExamens();

	List<Examen> listExamens1();

	Boolean verifierReponse(Long idQuestion, String Reponse);

	List<Question> consulterQuestions();
	public int calculerResultat(Long idExamen,Long idEtudiant);

	Page<Examen> consulterExamen1(String matiere, String niveau,int page, int size);

	List<Examen> consulterExamen2(String matiere, String niveau, int page, int size);
	public long timing();

}
