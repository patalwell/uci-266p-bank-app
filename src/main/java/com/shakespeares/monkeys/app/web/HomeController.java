package com.shakespeares.monkeys.app.web;

import com.shakespeares.monkeys.app.dto.UserRegistrationDto;
import com.shakespeares.monkeys.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {



	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model, UserRepository userRepository) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("=====================> " + auth.getName());
		model.addAttribute("userName", auth.getName());
//		System.out.println(userRepository.findByEmail(auth.getName()));
		return "index";
	}
}
