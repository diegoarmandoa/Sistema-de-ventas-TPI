package TPI.TPI.Controller;

import TPI.TPI.DTO.UserDTO;
import TPI.TPI.Enumeraciones.Rol;
import TPI.TPI.service.api.PersonaServiceAPI;
import TPI.TPI.service.api.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceAPI usuarioServiceAPI;
    @Autowired
    PersonaServiceAPI personaServiceAPI;

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

        model.addAttribute("rolUsuario", Rol.ROLE_USER);
        model.addAttribute("rolAdmin", Rol.ROLE_ADMIN);
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDTO registrationDto) {

       usuarioServiceAPI.save(registrationDto);

        return "redirect:/registration?success";
    }

    public String userActiveOrDesactive(){
        return "";
    }

    @GetMapping("/login")
    public String login() {
        return "dashboard/login.html";
    }

}
