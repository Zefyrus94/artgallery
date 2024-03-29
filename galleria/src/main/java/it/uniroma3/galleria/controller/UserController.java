

package it.uniroma3.galleria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.galleria.model.User_Role;
import it.uniroma3.galleria.model.User;
import it.uniroma3.galleria.service.User_Role_Service;
import it.uniroma3.galleria.service.UserService;

@Controller
@SessionAttributes("current_username")
public class UserController {

	@Autowired
	private UserService utenteService;
	@Autowired
	private User_Role_Service ruoloUtenteService;
	
	@GetMapping("/utente/{username}")
	public String mostraUtente(@PathVariable("username") String user, 
			@SessionAttribute(name="current_username") String username, Model model){
		User utente = utenteService.findByUsername(username);
		model.addAttribute(utente);
		return "area_riservata";
	}
	
	@RequestMapping("/accedi")
	public String accedi(@Valid @ModelAttribute User utente, Model model) {
		return "accesso";
	}
	
	@PostMapping("/registrazione")
	public String registraUtente(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
		String nextPage = "accesso";
		if (!bindingResult.hasErrors()) {
			if (!utenteService.alreadyExists(user.getUsername())) {
				utenteService.save(user);

				User_Role ruolo = new User_Role(user, "ROLE_USER");
				ruoloUtenteService.add(ruolo);
				
				model.addAttribute("utente", new User());
				model.addAttribute("successo", "Utente registrato correttamente");
			} else {
				model.addAttribute("errore", "Un utente con questo username è già presente nel sistema.");
			}
		}
		return nextPage;
	}
	@GetMapping("/user/info")
	public String showForm(User user) {
		return "user/info";
	}
	
}

