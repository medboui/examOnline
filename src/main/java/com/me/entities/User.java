package com.me.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
	private String prenom;
	private String login;
	private String passWord;
    @Column(name = "active")
    private int active;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "login"), inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    public User() {
    }

	public User(String login, String passWord) {
		super();
		this.login = login;
		this.passWord = passWord;
	}

	public User(String login, String passWord, int active, Set<Role> roles) {
		super();
		this.login = login;
		this.passWord = passWord;
		this.active = active;
		this.roles = roles;
	}

	public User(String nom, String prenom, String login, String passWord, int active, Set<Role> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.passWord = passWord;
		this.active = active;
		this.roles = roles;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

  
}
