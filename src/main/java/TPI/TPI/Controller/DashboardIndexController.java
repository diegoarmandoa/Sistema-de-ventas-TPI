package TPI.TPI.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DashboardIndexController {

    @RequestMapping("/dashboard/home")
    public String inicio(){
        return "/dashboard/index.html";
    }
}
