package com.ensah.core.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Ce controleur affiche la page index 
@Controller
public class InitiController {
	@RequestMapping("/")
	public String index(Model model) {

		return "index";
	}
}
