package TPI.TPI.Controller;

import TPI.TPI.DTO.UpdatePasswordDTO;
import TPI.TPI.DTO.UserDTO;
import TPI.TPI.Enumeraciones.Rol;
import TPI.TPI.service.api.PersonaServiceAPI;
import TPI.TPI.service.api.UsuarioServiceAPI;
import groovy.transform.WithReadLock;
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
    public String viewUsers(Model model) {
        model.addAttribute("listUsuarios", usuarioServiceAPI.getAll());
        model.addAttribute("updatePassword", new UpdatePasswordDTO());
        return "dashboard/usuarios.html";
    }

    @ModelAttribute("user")
    public UserDTO userRegistrationDto() {
        return new UserDTO();
    }

    @ModelAttribute("rolAdmin")
    public Rol rolAdmin() {
        return Rol.ROLE_ADMIN;
    }


    @ModelAttribute("rolUsuario")
    public Rol rolUsuario() {
        return Rol.ROLE_USER;
    }


    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDTO registrationDto) {

        usuarioServiceAPI.save(registrationDto);

        return "redirect:/registration?success";
    }

    @GetMapping("/estado")
    public String PersonaActiveOrDesactive(@RequestParam Integer id, @RequestParam Boolean estado) {
        personaServiceAPI.usuarioSetEstado(estado, id);
        return "redirect:/dashboard/usuarios/view";
    }

    @GetMapping("/login")
    public String login() {
        return "dashboard/login.html";
    }

    @GetMapping("/registrar")
    public String registrar() {

        return "dashboard/addUsuario";
    }

    @GetMapping("/updatePassword")
    public String UpdatePasword(Model model) {
        model.addAttribute("updatePassword", new UpdatePasswordDTO());
        return "";
    }

    @PostMapping("/updatePassword")
    public String UpdatePasword(@ModelAttribute UpdatePasswordDTO updatePasswordDTO) {
        usuarioServiceAPI.setPassword(updatePasswordDTO);
        return "redirect:/dashboard/usuarios/view";
    }


}
