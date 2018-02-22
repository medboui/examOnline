package com.me.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.me.entities.Etudiant;
import com.me.entities.Examen;
import com.me.entities.Question;
import com.me.entities.Response;

public interface ResponseRepository extends JpaRepository<Response,Long> {
	@Query("select r from Response r where r.examen=:x and r.etudiant=:y ") //on utilise une requette Hql qui order les comptes by date
	public List <Response> findByExEt(@Param("x")Examen idExamen,@Param("y")Etudiant idEtudiant);
}
