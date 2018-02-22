package com.me.sec;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.entities.Etudiant;

@Controller
public class SecurityController {
	@RequestMapping(value="/login")
	public String login(HttpSession session) {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//String currentPrincipalName = authentication.getName();
		//session.addAttribute("x",new Etudiant());
		//session.putValue("x", 34);
		return "login" ;
	}
	@RequestMapping(value="/home")
	public String home() {
		return "redirect:home" ;
	}
	@RequestMapping(value="/403")
	public String accessDenied() {
		return "403" ;
	}

}
