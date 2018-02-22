package com.me.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.me.entities.Examen;
import com.me.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {
	@Query("select q from Question q ")
	public List<Question> listQuestions();
	@Query("select q1 from Question q1 where q1.examen=:x") //on utilise une requette Hql qui order les comptes by date
	public List <Question> listQuestions1(@Param("x")Examen idExamen);
	@Query("select q2 from Question q2 where q2.examen=:x and q2.NumQuestion=:y ") //on utilise une requette Hql qui order les comptes by date
	public Question findByNmQ(@Param("x")Examen idExamen,@Param("y")Integer NumQuestion);


}
