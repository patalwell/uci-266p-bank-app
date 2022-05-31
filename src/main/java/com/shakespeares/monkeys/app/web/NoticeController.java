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
        logger.info(String.format("============== Download Licences for User %s =============", auth.getName()));

        Resource resource = new ClassPathResource("licenses" + File.separator + licenseName);

        return ResponseEntity.ok()
                .contentLength(resource.getFile().length())
                .body(resource);
    }
}
