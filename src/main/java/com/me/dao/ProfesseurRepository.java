package com.me.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.me.entities.Professeur;

public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {


}
