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
		Boolean isUsernameValid= validateCredentials(registrationDto.getUsername());
		Boolean isPasswordValid=validateCredentials(registrationDto.getPassword());
		if (isUsernameValid && isPasswordValid){
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}
		else if(isUsernameValid.equals(false)){
			return "redirect:/registration?invalidUsername";
		}
		else if(isPasswordValid.equals(false)) {
			return "redirect:/registration?invalidPassword";
		}
		else {
			return "redirect:/registration?registrationFailed";
		}
	}
}
