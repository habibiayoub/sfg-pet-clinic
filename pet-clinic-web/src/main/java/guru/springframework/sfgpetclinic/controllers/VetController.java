package guru.springframework.sfgpetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.VetService;

@Controller
@RequestMapping("/vets")
public class VetController {
	
	@Autowired
	private VetService vetService;
	
	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listVets(Model model) {
		
		model.addAttribute("vets", vetService.findAll());
		return "vets/index";
	}

}
