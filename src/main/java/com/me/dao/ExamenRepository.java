package com.me.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.me.entities.Examen;

public interface ExamenRepository extends JpaRepository<Examen,Long> {
	@Query("select e from Examen e ") //on utilise une requette Hql qui order les comptes by date
	public Page <Examen> listExamens(Pageable pageable);
	@Query("select e1 from Examen e1 where e1.nomMatiere=:x or e1.niveauEtudes=:y") //on utilise une requette Hql qui order les comptes by date
	public List <Examen> listExamens2(@Param("x")String matiere,@Param("y")String annee);
	@Query("select e1 from Examen e1 where e1.nomMatiere=:x or e1.niveauEtudes=:y") //on utilise une requette Hql qui order les comptes by date
	public Page <Examen> listExamens1(@Param("x")String matiere,@Param("y")String annee,Pageable pageable);
	@Query("select e1 from Examen e1 where e1.nomMatiere=:x")
	public Page <Examen> listExamens3(@Param("x")String matiere,Pageable pageable);
	

	


}
