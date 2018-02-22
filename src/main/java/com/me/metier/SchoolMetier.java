package com.me.metier;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.me.Chrono;
import com.me.dao.EtudiantRepository;
import com.me.dao.ExamenRepository;
import com.me.dao.QuestionRepository;
import com.me.dao.ResponseRepository;
import com.me.entities.Etudiant;
import com.me.entities.Examen;
import com.me.entities.Question;
import com.me.entities.Response;

import groovyjarjarasm.asm.commons.StaticInitMerger;

@Service
public class SchoolMetier implements ISchoolMetier{

	@Autowired
	private ExamenRepository examenRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private ResponseRepository responseRepository;
	
	@Override
	public Examen consulterExamen(Long idExamen) {
		Examen ex=examenRepository.findOne(idExamen);
		//List<Question> questions=questionRepository.listQuestions();
		if(ex==null) throw new RuntimeException("Introuvable");
		
		return ex;
	}
	public int calculerResultat(Long idExamen,Long idEtudiant) {
		int s=0;
		Examen ex=examenRepository.findOne(idExamen);
		Etudiant et=etudiantRepository.findOne(idEtudiant);
		List<Response> responses=responseRepository.findByExEt(ex, et);
		for(Response r:responses) {
			s+=r.getRes()? 1 : 0;
		}
		
		return s;
	}
	@Override
	public long timing() {
		  Chrono chrono =new Chrono();
		  chrono.start();
		  long t=chrono.getDureeMs();
		 
		return t;
		 
	    }
	
	@Override
	public List<Examen> consulterExamen2(String matiere,String niveau,int page, int size ) {
		List<Examen> listex=examenRepository.listExamens2(matiere, niveau);

		
		return listex;
	}
	@Override
	public Page<Examen> consulterExamen1(String matiere,String niveau,int page, int size ) {
		Page<Examen> Pex=examenRepository.listExamens1(matiere, niveau,new PageRequest(page,size) );
		return Pex;
	}
	
	@Override
	public List<Question> consulterQuestions() {
		List<Question> questions=questionRepository.listQuestions();
		return questions;
	}
	
	@Override
	public Boolean verifierReponse(Long idQuestion, String response) {
	      Question q=questionRepository.findOne(idQuestion);
	         if((response).equals(q.getBonChoix())) return true;
		
	         else return false;
		
	
	}
	@Override
	public Page<Examen> listExamens() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Examen> listExamens1() {
		List<Examen> listEX=examenRepository.findAll();
		return listEX;
	}

}
