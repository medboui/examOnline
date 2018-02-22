package com.me.web; 

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.Module;
import com.me.Chrono;
import com.me.dao.EtudiantRepository;
import com.me.dao.ExamenRepository;
import com.me.dao.ProfesseurRepository;
import com.me.dao.QuestionRepository;
import com.me.dao.ResponseRepository;
import com.me.dao.UserRepository;
import com.me.entities.Etudiant;
import com.me.entities.Examen;
import com.me.entities.Professeur;
import com.me.entities.Question;
import com.me.entities.Response;
import com.me.entities.Role;
import com.me.entities.User;
//import com.me.entities.user;
import com.me.metier.ISchoolMetier;

@Controller
//@RequestMapping(value="/School")
public class SchoolController {
	
	@Autowired
	private ISchoolMetier iSchoolMetier;
	@Autowired
	private ExamenRepository examenRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ResponseRepository responseRepository;
	@Autowired
	private EtudiantRepository etudiantRepository;
	int j=0;
	HttpServletRequest request;
	Module module1;
	int i=1;
	@Secured(value={"ADMIN"})
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String index(Model model){
		//model.addAttribute("etudiant",new Etudiant());
		return "index"; 
	}

	@RequestMapping(value="/formE",method=RequestMethod.GET)
	public String formEtudiant(Model model){
		model.addAttribute("etudiant",new Etudiant());
		return "FormEtudiant";
	}
	@Secured(value={"ADMIN","PROFESSEUR"})
	@RequestMapping(value="/formP",method=RequestMethod.GET)
	public String formProfesseur(Model model){
		model.addAttribute("professeur",new Professeur());
		return "FormProfesseur";
	}
	//@Secured(value={"ADMIN","ETUDIANT"})
	@RequestMapping(value="/SaveEtudiant",method=RequestMethod.POST)
	public String save(@Valid Etudiant et,BindingResult bindingResult) throws IllegalStateException, IOException{			//on effectue la validation by using Valid et si on des messages 
				if (bindingResult.hasErrors()){					//on récupére le fichier image by using MultipartFile 
					return "FormEtudiant";
				}
				Set<Role> setA = new HashSet();
				setA.add(new Role("ETUDIANT"));
				etudiantRepository.save(et);
				userRepository.save(new User(et.getNom(),et.getPrenom(),et.getLogin(),et.getPassWord(),1,setA));
		
		return "redirect:/home"; //ou encore return "redirect:Index"
	}
	@RequestMapping(value="/SaveProfesseur",method=RequestMethod.POST)
	public String save(@Valid Professeur pr,BindingResult bindingResult) throws IllegalStateException, IOException{			//on effectue la validation by using Valid et si on des messages 
				if (bindingResult.hasErrors()){					//on récupére le fichier image by using MultipartFile 
					return "FormProfesseur";
				}
				Set<Role> setA = new HashSet();
				setA.add(new Role("PROFESSEUR"));
				
				professeurRepository.save(pr);
				userRepository.save(new User(pr.getNom(),pr.getPrenom(),pr.getLogin(),pr.getPassWord(),1,setA));
				
		
		return "redirect:/home"; //ou encore return "redirect:Index"
	}
	@RequestMapping("/consulterEtudiant")
	private String consulterEtudiant(Model model,@RequestParam (name="idEtudiant") Long idEtudiant){
		try{
			Etudiant et=etudiantRepository.findOne(idEtudiant);
			model.addAttribute("etudiant",et);

		}
		catch(Exception e){
			model.addAttribute("exception",e);
		}
		
		return "etudiant";
	}
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formExamen(Model model){
		model.addAttribute("examen",new Examen());
		model.addAttribute("question1",new Question());
		return "ok2";
	}
	@RequestMapping(value="/formF",method=RequestMethod.GET)
	public String formExamenF(Model model){
		model.addAttribute("examen",new Examen());
		model.addAttribute("question1",new Question());
		return "Fexam";
	}
	@RequestMapping(value="/form1",method=RequestMethod.GET)
	public String formExamen1(Model model){
		//int j []= {1,2,3,4,5,6,7,8,9,10};
		model.addAttribute("question1",new Question());
		model.addAttribute("NumQuestion",j);
		//model.addAttribute("NumQuestion",j);
		
		return "FormExamenN2";
	}
	@RequestMapping(value="/SaveExamen1",method=RequestMethod.POST)
	public String save1(Examen ex,BindingResult bindingResult) throws IllegalStateException, IOException{ //on effectue la validation by using Valid et si on des messages 
				examenRepository.save(ex);
			
				Long id=ex.getId();
				
				
		return "redirect:/form1?idExamen="+id+"&NumQuestion="+j; 
	}
	@RequestMapping(value="/SaveExamen2",method=RequestMethod.POST)
	public String save2(Question q,BindingResult bindingResult,Model model, @RequestParam (name="idExamen") Long idExamen,@RequestParam (name="NumQuestion") Integer NumQ) throws IllegalStateException, IOException{			//on effectue la validation by using Valid et si on des messages 	
		j=1+j;
		q.setExamen(iSchoolMetier.consulterExamen(idExamen));
		NumQ=j;
		q.setNumQuestion(NumQ);
		questionRepository.save(q);
	
	   model.addAttribute("NumQuestion",j); 
//	   if (j==10) {
//			return "redirect:/index1";
//		}
		return "redirect:/form1?idExamen="+idExamen+"&NumQuestion="+NumQ; //ou encore return "redirect:Index"
		
	}
	@RequestMapping("/consulterExamens")
	private String consulter(Model model,Long codeEx,String matiere,String niveau,@RequestParam(name="page",defaultValue="0") int p){
		try{
			Examen ex=iSchoolMetier.consulterExamen(codeEx);
			Page <Examen> pageExamens1=iSchoolMetier.consulterExamen1(matiere,niveau, p, 4);
			List <Examen> pageExamens=iSchoolMetier.listExamens1();
			//****
			int pagesCount=pageExamens1.getTotalPages();
			int[]pages=new int[pagesCount];
			for (int i=0;i<pagesCount;i++){
				pages[i]=i;
			}
			model.addAttribute("pages",pages);
			model.addAttribute("pageCourante",p);
			//**
			model.addAttribute("pageExamens1",pageExamens1.getContent());
			model.addAttribute("listExamens",pageExamens);
			model.addAttribute("examen",ex);

		}
		catch(Exception e){
			model.addAttribute("exception",e);
		}
		 
		return "examens";
	}
	@RequestMapping(value = "/index")
	private String debut(Model model,@RequestParam (name="idExamen") Long idExam) throws InterruptedException{
		//iSchoolMetier.timing();
	
		//Chrono c1=new Chrono();
		//c1.start();
		
			Question q=questionRepository.findByNmQ(iSchoolMetier.consulterExamen(idExam),i);
			//System.out.println(q.q)
//			int j []= {1,2,3,4,5,6,7,8,9,10};
//			List <Question> listQuestions=questionRepository.listQuestions1(iSchoolMetier.consulterExamen(idExam));
			model.addAttribute("NumResponses",j);
			//model.addAttribute("listQuestions", listQuestions);
			model.addAttribute("QuestionN",q);
			model.addAttribute("QuestionId",q.getId());
			model.addAttribute("num",i);
		
			//Thread.sleep(5000);
		//c1.stop();
		//long t1=c1.getDureeMs();
		//model.addAttribute("timing",t1);
			if(i>10
				) { return "redirect:http://localhost:8008/index1";}
		 return "ok2";
	}
	@RequestMapping(value = "/endExam")
	private String consulter(Model model,@RequestParam(name="idE")Long idE,@RequestParam(name="idEt")Long idEt,Principal principal) throws InterruptedException{
		i=1;
		
		String user= principal.getName();
		Etudiant etudiant=etudiantRepository.findByLogin(user);
		idEt=etudiant.getId();
		int res=iSchoolMetier.calculerResultat(idE, idEt);
		model.addAttribute("etudiant",etudiant);
		model.addAttribute("res", res);
		 return "etudiant"; 
	}
	
	@RequestMapping(value="/index1") 
	public String Index(Model model,@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="matiere",defaultValue="") String matiere,@RequestParam(name="niveau",defaultValue="") String niveau){
		Page <Examen> pageExamens1=iSchoolMetier.consulterExamen1(matiere,niveau, p, 4);
		Page<Examen> pageExamens=examenRepository.listExamens1(matiere,niveau,new PageRequest(p,5));
		int pagesCount=pageExamens.getTotalPages();
		int[]pages=new int[pagesCount];
		for (int i=0;i<pagesCount;i++){
			pages[i]=i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageExamens1", pageExamens1);
		model.addAttribute("pageCourante",p);
		model.addAttribute("matiere",matiere);
		model.addAttribute("niveau",niveau);
		
		return "examens";
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		examenRepository.delete(id);
		return "redirect:index1";
	}
	@RequestMapping(value="/passer")
	public String passer(Long id){
		return "redirect:index?idExamen="+id;
	}
	@RequestMapping(value="/passer1")
	public String passer1(@RequestParam(name="idE")Long idE,@RequestParam(name="idQ")Long Nq,Model model,Principal principal){
		System.out.println("ok ca passe");
		i=i+1;
		
		Question q=questionRepository.findOne(Nq);
		System.out.println("ok ca passe 222222 ");
		responseRepository.save(new Response(q.getEnonce(),false,iSchoolMetier.consulterExamen(idE),q));
		System.out.println("ok ca passe 33333 ");
		model.addAttribute("num",i);
		String user= principal.getName();
		Etudiant etudiant=etudiantRepository.findByLogin(user);
		System.out.println(i);
		if(i>10) { 
			model.addAttribute("idE",idE);
			return "redirect:endExam?idE="+idE+"&idEt="+etudiant.getId();}
		return "redirect:index?idExamen="+idE+"&NumQuestion="+i;
	}
	  
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		Etudiant et=etudiantRepository.getOne(id);
		model.addAttribute("etudiant",et);
		return "EditEtudiant";
	}
	@RequestMapping(value="/saveReponse",method=RequestMethod.GET)
	public String saveResponse(@RequestParam(name="idQuestion")Long idQuestion,@RequestParam(name="idExamen")Long id,Model model,
			@RequestParam(name="choice")String choix,Principal principal){
		Question q=questionRepository.findOne(idQuestion);
		boolean res=iSchoolMetier.verifierReponse(idQuestion, choix);
		String user= principal.getName();
		Etudiant etudiant=etudiantRepository.findByLogin(user);
		responseRepository.save(new Response(q.getEnonce(),res,iSchoolMetier.consulterExamen(id),etudiant, q)); 
		
		return "redirect:index?idExamen="+id;
	} 
	public void saveResponseLite(Long idQuestion,Long id){
		Question q=questionRepository.findOne(idQuestion);
		responseRepository.save(new Response(q.getEnonce(),false,iSchoolMetier.consulterExamen(id),q)); 
		
	} 
	@RequestMapping(value="/saveReponse1",method=RequestMethod.POST)
	public String saveResponse1(@RequestParam(name="idQuestion")Long idQuestion,@RequestParam(name="idExamen")Long idE,Model model,
			@RequestParam(name="choice")String choix,Principal principal) throws InterruptedException{
		long t1=iSchoolMetier.timing();
		Question q=questionRepository.findOne(idQuestion);
		boolean res=iSchoolMetier.verifierReponse(idQuestion, choix);
		String user= principal.getName();
		Etudiant etudiant=etudiantRepository.findByLogin(user);
		responseRepository.save(new Response(q.getEnonce(),res,iSchoolMetier.consulterExamen(idE),etudiant,q));
		i=i+1;
		model.addAttribute("num",i); 
		
		//if(i>7) {
		//return "redirect:endExam";}
		if(i>10) { 
			model.addAttribute("idE",idE);
			return "redirect:endExam?idE="+idE+"&idEt="+etudiant.getId();}
		System.out.println(i);
		return "redirect:index?idExamen="+idE+"&NumQuestion="+i;
	}
	
	  //RequestMapping(value = "/username", method = RequestMethod.GET)
	    public String currentUserName(Principal principal,Model model) {
	        String user= principal.getName();
	        model.addAttribute("user", user);
	        return "userView";
	   
	}
}
