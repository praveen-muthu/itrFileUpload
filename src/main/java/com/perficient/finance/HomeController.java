package com.perficient.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itreturns")
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
