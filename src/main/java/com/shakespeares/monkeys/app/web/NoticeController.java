package com.shakespeares.monkeys.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@Controller
public class NoticeController {

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @GetMapping(value = "/downloadLicenses")
    public ResponseEntity<Resource> downloadLicense(
            @RequestParam(value = "license", required = true) String licenseName) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info(String.format("============== Download Licenses for User %s =============", auth.getName()));

        Resource resource = null;

        switch (licenseName) {
            case "terms":
                resource = new ClassPathResource("licenses/terms-of-use.txt");
                break;
            case "spring":
                resource = new ClassPathResource("licenses/spring-boot-license.txt");
                break;
            case "thymeleaf":
                resource = new ClassPathResource("licenses/thymeleaf-license.txt");
                break;
            case "projectlombok":
                resource = new ClassPathResource("licenses/projectlombok-license.txt");
                break;
            default:
                throw new FileNotFoundException("File Not Found");
        }


        return ResponseEntity.ok()
                .contentLength(resource.getFile().length())
                .body(resource);
    }

    @GetMapping("/redirect")
    public String redirect(@RequestParam("url") String url) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info(String.format("============== Redirect for User %s =============", auth.getName()));
        return "redirect:".concat(url);
    }

}
