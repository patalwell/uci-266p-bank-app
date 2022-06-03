package com.shakespeares.monkeys.app.config;

import com.shakespeares.monkeys.app.model.Role;
import com.shakespeares.monkeys.app.model.User;
import com.shakespeares.monkeys.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Role");
        //Get Env Var for Admin
        user.setUsername("admin");
        //this should ideally be obfuscated with ENV_VAR or Secrets Manager Solution
        user.setPassword(passwordEncoder.encode("changeme"));
        user.setBalance(new BigDecimal("0.00"));
        user.setRoles(Arrays.asList(new Role("ADMIN_ROLE")));
        userRepository.save(user);
        alreadySetup = true;
    }
}