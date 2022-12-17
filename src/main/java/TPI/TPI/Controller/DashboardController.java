package TPI.TPI.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DashboardController {

    @RequestMapping("/dashboard/home")
    public String inicio(){
        return "dashboard/index.html";
    }
    @RequestMapping ("/dashboard/login")
    public String login(){
        return "hola";
       
    }

    @GetMapping("/dashboard")
    public String hola(){
        return "";
    }
}
