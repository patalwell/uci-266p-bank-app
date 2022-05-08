package com.shakespeares.monkeys.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.io.*;

@Controller
public class Notices {

    @GetMapping(value = "/downloadLicenses")
    public ResponseEntity<Resource> downloadLicense(
            @RequestParam(value = "license", required = true) String licenseName) throws IOException {

        System.out.println("liscenceName");

        Resource resource = new ClassPathResource("licenses" + File.separator + licenseName);
        return ResponseEntity.ok()
                .contentLength(resource.getFile().length())
                // add this header to download file as attachment
                // .header("Content-Disposition", "attachment; filename=" + licenseName)
                .body(resource);
    }

    @GetMapping("/redirect")
    public String redirect(@RequestParam("url") String url) {
        if (!StringUtils.isEmptyOrWhitespace(url)) {

        }
        else {
            url = "/";
        }

        return "redirect:".concat(url);
    }

}
