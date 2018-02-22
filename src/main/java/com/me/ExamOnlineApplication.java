package com.me;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.me.dao.EtudiantRepository;
import com.me.dao.ExamenRepository;
import com.me.dao.QuestionRepository;
import com.me.dao.RoleRepository;
import com.me.dao.UserRepository;
import com.me.entities.Examen;
import com.me.entities.Question;
import com.me.entities.Role;
import com.me.entities.User;

@SpringBootApplication
@ComponentScan(basePackages="com.me")
public class ExamOnlineApplication implements CommandLineRunner {

	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private ExamenRepository examenRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
//		Chrono chrono=new Chrono();
//		chrono.start();
		ApplicationContext ctx=SpringApplication.run(ExamOnlineApplication.class, args);
		ExamenRepository examenRepository=ctx.getBean(ExamenRepository.class);
//		chrono.stop();
//		System.out.println(chrono.getDureeMs());
	}
	@Override
	public void run(String... arg0) throws Exception {
		
		//Examen ex1=examenRepository.save(new Examen("maths",2));
		Role role=new Role("TEST");
		Set<Role> roles=new HashSet<Role>();
		roles.add(role);
		userRepository.save(new User("test","test","test","test",1,roles));
		roleRepository.save(role);
		/*questionRepository.save(new Question("la question 2","a1","b1","c1","a1",ex1));
		questionRepository.save(new Question("la question 3","a2","b2","c2","b2",ex1));
		questionRepository.save(new Question("la question 3","a3","b3","c3","b3",ex1));
		questionRepository.save(new Question("la question 3","a2","b2","c2","b2",ex1));
		
		
		
		List<Examen> ets= examenRepository.findAll();
		 ets.forEach(c->{
	    	   System.out.println(c.getNomMatiere());
	    	   //System.out.println(c.getQuestions());
		 });
		 List<Question> ques= questionRepository.findAll();
		 ques.forEach(q->{
	    	   System.out.println(q.getBonChoix());
	    	   System.out.println(q.getQuestion());
	    	   
		 });*/
		
		
		
		 }
}
