package com.shakespeares.monkeys.app.web;

import com.shakespeares.monkeys.app.dto.UserRegistrationDto;
import com.shakespeares.monkeys.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(RegistrationController.class);

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

		logger.info(String.format("================= Register Account for User: %s ================",registrationDto.getUsername()));
		boolean isValidUsername = false;
		boolean isValidPassword = false;
		boolean isValidBalance = false;
		boolean doesUsernameExist = false;

		if (registrationDto.getUsername() != null){
			isValidUsername= validateCredentials(registrationDto.getUsername());
			doesUsernameExist = userService.doesUsernameExist(registrationDto.getUsername());
		}
		if (registrationDto.getPassword() != null) {
			isValidPassword = validateCredentials(registrationDto.getPassword());
		}
		if (registrationDto.getBalance() != null){
			isValidBalance = validateNumericInput(registrationDto.getBalance());
		}
		if (isValidUsername && isValidPassword && isValidBalance && !doesUsernameExist){
			userService.save(registrationDto);
			return "redirect:/registration?success";
		}

		if(!isValidUsername){
			logger.error("user entered invalid user name");
			return "redirect:/registration?invalidUsername";
		}
		else if(!isValidPassword) {
			logger.error("user entered invalid password");
			return "redirect:/registration?invalidPassword";
		}
		else if(doesUsernameExist){
			logger.error("username already exists");
			return "redirect:/registration?usernameExists";
		}
		else {
			logger.error("user entered invalid balance");
			return "redirect:/registration?invalidBalance";
		}
	}
}
