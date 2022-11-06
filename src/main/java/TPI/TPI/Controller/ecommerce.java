package TPI.TPI.Controller;

import TPI.TPI.Entity.CarritoDao;
import TPI.TPI.Entity.Productos;
import TPI.TPI.Repository.ProductosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class ecommerce {
@Autowired
    ProductosRepositorio repositorio;

    List<CarritoDao> carritoDao = new ArrayList();
    @GetMapping("/agregados")
    public String agregados(@RequestParam Map<String, Object> params, Model model, Productos producto){


        List<Productos> producto1 = new ArrayList<>();
        Productos productos = new Productos();
        List<CarritoDao> carro= carritoDao;
        for(CarritoDao x: carritoDao){

            producto1.add(repositorio.findAllById(Collections.singleton(x.getIdproducto())).get(0));
        }
        model.addAttribute("carritos", carro);//enviando la lista
        model.addAttribute("productos", producto1);//enviando la lista
        return "carrito";
    }
    @GetMapping("/ecommerce")
    public String ecommerce(@RequestParam Map<String, Object> params, Model model, Productos producto){
        List<Productos> producto1 = repositorio.findAll();
        List<CarritoDao> carro= carritoDao;
        model.addAttribute("productos", producto1);//enviando la lista
        model.addAttribute("carritos", carro);//enviando la lista
        return "ecommerce";
    }

    @PostMapping("/{id}/carrito")
    public String carrito(@PathVariable Integer id, @Validated Productos productos, BindingResult bindingResult, RedirectAttributes redirect, Model model) {
        CarritoDao carritoDao1 = new CarritoDao();
        carritoDao1.setIdproducto(id);
        carritoDao1.setCantidad(productos.getCantidad());

        carritoDao.add(carritoDao1);


        return "redirect:/ecommerce";
    }
}
