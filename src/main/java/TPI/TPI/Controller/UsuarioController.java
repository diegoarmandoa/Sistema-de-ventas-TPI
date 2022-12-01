package TPI.TPI.Controller;

import TPI.TPI.service.api.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/agregar")
    public String addUser(){
        return "";
    }

    @GetMapping("/editar")
    public String updateUser(){
        return "";
    }

    @GetMapping("/login")
    public String login (){
        return "dashboard/login.html";
    }

}
