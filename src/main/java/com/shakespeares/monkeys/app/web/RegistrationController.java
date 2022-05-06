package com.shakespeares.monkeys.app.web;

import com.shakespeares.monkeys.app.dto.UserRegistrationDto;
import com.shakespeares.monkeys.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.shakespeares.monkeys.app.util.Validation.validateCredentials;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserService userService;

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		Boolean resultE= validateCredentials(registrationDto.getUsername());
		Boolean resultP=validateCredentials(registrationDto.getPassword());
		if (resultE && resultP){
			userService.save(registrationDto);
			System.out.println("username:" + resultE + "\n password:" + resultP);
			return "redirect:/registration?success";
		}
		if(resultE.equals(false)){
			System.out.println("username:" + resultE);
			return "redirect:/registration?usernameError";

		}
		else{
			System.out.println("password:" + resultP);
			return "redirect:/registration?passwordError";
		}
	}
}
