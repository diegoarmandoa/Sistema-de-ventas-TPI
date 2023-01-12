package TPI.TPI.Controller;

import TPI.TPI.DTO.UserDTO;
import TPI.TPI.Enumeraciones.Rol;
import TPI.TPI.service.api.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    UsuarioServiceAPI usuarioServiceAPI;

    @GetMapping("/login")
    public String login() {
        return "dashboard/login.html";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/ecommerce";
    }

    @GetMapping("/error")
    public String error() {
        return "dashboard/404.html";
    }


    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user",new UserDTO() );
        model.addAttribute("rolAdmin", Rol.ROLE_ADMIN);
        model.addAttribute("rolUsuario", Rol.ROLE_USER);
        if (!usuarioServiceAPI.existeUsuario()) return "registration";
        return "redirect:/login";
    }

    @PostMapping("/registro")
    public String registerUserAccount(@ModelAttribute("user") UserDTO registrationDto) {

        usuarioServiceAPI.save(registrationDto);

        return "redirect:/login";
    }







}


