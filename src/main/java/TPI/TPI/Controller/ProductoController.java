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
@RequestMapping("/dashboard/productos")
public class ProductoController {
    @Autowired
    private ProductoServiceAPI productoServiceAPI;

    //listo
    @RequestMapping("/view")
    public String viewProductos(Model model){
        model.addAttribute("productosList", productoServiceAPI.getAll());
        return "dashboard/productos.html";
    }
    //listo
    @RequestMapping("/new")
    public String newProductos(){
        return "dashboard/addProductos.html";
    }

    @GetMapping("/saveProductos/{id}")
    public String showSave(@PathVariable("id") Integer id, Model model){
        if(id != null){
            model.addAttribute("producto", productoServiceAPI.get(id));
        }
        return "dashboard/editProductos.html";
    }

    //listo
    @PostMapping("/saveProductos")
    public String save(Productos productos, Model model){
        productoServiceAPI.save(productos);
        return "redirect:view";
    }

    //listo
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        productoServiceAPI.delete(id);
        return "redirect:../view";
    }
}
