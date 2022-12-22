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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceAPI usuarioServiceAPI;

    @GetMapping("/view")
    public String viewUsers( Model model){
        model.addAttribute("listUsuarios",usuarioServiceAPI.getAll());
        return "dashboard/usuarios.html";
    }

    @ModelAttribute("user")
    public UserDTO userRegistrationDto() {
        return new UserDTO();
    }



    @GetMapping
    public String showRegistrationForm(Model model) {

        model.addAttribute("rolUsuario", Rol.USER);
        model.addAttribute("rolAdmin", Rol.ADMIN);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDTO registrationDto) {

       usuarioServiceAPI.save(registrationDto);

        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
