package com.shakespeares.monkeys.app.web;

import com.shakespeares.monkeys.app.dto.UserRegistrationDto;
import com.shakespeares.monkeys.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.shakespeares.monkeys.app.util.Validation.validateCredentials;
import static com.shakespeares.monkeys.app.util.Validation.validateNumericInput;

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
		boolean isValidUsername = false;
		boolean isValidPassword = false;
		boolean isValidBalance = false;
		if (registrationDto.getUsername() != null){
			isValidUsername= validateCredentials(registrationDto.getUsername());
		}
		if (registrationDto.getPassword() != null) {
			isValidPassword = validateCredentials(registrationDto.getPassword());
		}
		if (registrationDto.getBalance() != null){
			isValidBalance = validateNumericInput(registrationDto.getBalance());
		}
		if (isValidUsername && isValidPassword && isValidBalance){
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}

		if(!isValidUsername){
			return "redirect:/registration?invalidUsername";
		}
		else if(!isValidPassword) {
			return "redirect:/registration?invalidPassword";
		}
		else {
			return "redirect:/registration?invalidBalance";
		}
	}
}
