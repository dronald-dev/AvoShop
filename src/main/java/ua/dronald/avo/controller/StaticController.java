package ua.dronald.avo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class StaticController {
    @GetMapping
    public String about() {
        return "about";
    }
}
