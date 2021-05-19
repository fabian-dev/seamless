package dev.sch8fa.seamless;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("action", "index");
        return "index";
    }

}
