package com.me.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		//ici on definit les users et les roles
		//par la suite on va utiliser les BD au lieu de inMemoryAuthentication
	//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN","USER");
	//auth.inMemoryAuthentication().withUser("anass").password("anass").roles("USER");
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select  id as principal, pass_word as credentials, active from user where login=?")
			.authoritiesByUsernameQuery("select login as principal, role as role from user_role where login=?")
			.rolePrefix("");
			//.passwordEncoder(new Md5PasswordEncoder());
		
	}
protected void configure (HttpSecurity http) throws Exception {
	//on definit les strategies d'auth
	//http.formLogin().loginPage("/login");
	
	http.csrf().disable()
	.authorizeRequests()
	.antMatchers("/css/**","/js/**","/home").permitAll()
	.anyRequest()
	.authenticated()
		.and()
	.formLogin()
		.loginPage("/login")
			.permitAll()
	.defaultSuccessUrl("/home.html");
	 http.authorizeRequests().antMatchers("/index1","/formE","/formP","/SaveEtudiant",
				"/SaveProfesseur","/consulterEtudiant","/form","/formF","/form1","/SaveExamen1","/SaveExamen2",
				"/consulterExamens","/index","/endExam","/index1","/supprimer","/passer","/passer1","/edit","/saveReponse","/saveReponse1").hasRole("ADMIN");
	http.authorizeRequests().antMatchers("/index1","/passer","/passer1","/saveReponse","/saveReponse1","/form","/formF","/form1",
			"/consulterEtudiant","/form","/formF","/form1").hasRole("ETUDIANT");
	http.authorizeRequests().antMatchers("/index1","/form","/formF","/form1","/SaveExamen1","/SaveExamen2").hasRole("PROFESSEUR");
	http.authorizeRequests().antMatchers("/index1","/formE","/formP","/SaveEtudiant",
			"/SaveProfesseur","/consulterEtudiant","/form","/formF","/form1","/SaveExamen1","/SaveExamen2",
			"/consulterExamens","/index","/endExam","/index1","/supprimer","/passer","/passer1","/edit","/saveReponse","/saveReponse1").hasRole("TEST");
		http.exceptionHandling().accessDeniedPage("/403");
		
	}

}
