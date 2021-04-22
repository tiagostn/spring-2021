package org.ts.spring21.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
@Log4j2
public class InfoController {

    @GetMapping
    public String info() {
        log.info("Info");
        return "yellow!";
    }
}
