package com.sktelecom.t1.Applications;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class frontPage {
    @RequestMapping("/frontPage")
    public String frontPage(Model model){
        model.addAttribute("TeamName", "SK Telecom T1");

        return "frontPage";
    }
}
