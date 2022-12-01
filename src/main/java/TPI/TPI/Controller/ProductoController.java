package TPI.TPI.Controller;

import TPI.TPI.Entity.Productos;
import TPI.TPI.service.api.ProductoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductoController {
    @Autowired
    private ProductoServiceAPI productoServiceAPI;

    @RequestMapping("/")
    public String viewProductos(Model model){
        model.addAttribute("list", productoServiceAPI.getAll());
        return "productos";
    }

    @GetMapping("/saveProductos/{id}")
    public String showSave(@PathVariable("id") Long id, Model model){
        if(id != null){
            model.addAttribute("producto", productoServiceAPI.get(id));
        }
        return "saveProductos";
    }

    @PostMapping("/saveProductos")
    public String save(Productos productos, Model model){
        productoServiceAPI.save(productos);
        return "redirect:/";
    }

    public String delete(@PathVariable Long id, Model model){
        productoServiceAPI.delete(id);
        return "redirect:/";
    }
}
