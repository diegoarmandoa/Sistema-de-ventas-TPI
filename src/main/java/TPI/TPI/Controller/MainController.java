package TPI.TPI.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
    @GetMapping("/login")
    public String login() {
        return "dashboard/login.html";
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/ecommerce";
    }
}


