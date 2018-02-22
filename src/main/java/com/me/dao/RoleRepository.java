package com.me.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.me.entities.Etudiant;
import com.me.entities.Role;
import com.me.entities.User;


public interface RoleRepository extends JpaRepository<Role,Long> {

	//@Query("select c from Client c ") //on utilise une requette Hql qui order les comptes by date
	//public Page <Etudiant> listClients(Pageable pageable);

}
