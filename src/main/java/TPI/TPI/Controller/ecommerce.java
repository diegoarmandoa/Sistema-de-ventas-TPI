package TPI.TPI.Controller;

import TPI.TPI.Entity.*;
import TPI.TPI.Repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping
public class ecommerce {
    @Autowired
    ProductosRepositorio repositorio;

    @Autowired
    PedidosRepositorio pedidosRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    PersonaRepositorio personaRepositorio;

    List<CarritoDao> carritoDao = new ArrayList();

    @PostMapping("/agregarCliente")
    public String guardarCliente(@RequestParam Map<String, Object> params, Model model, Usuarios usuario, Clientes cliente, RedirectAttributes redirect) {
        try {


            if(!usuarioRepositorio.buscarUsuarioPassword(usuario.getUsuario(), usuario.getPassword()).getId_Usuario().equals("")){
                redirect.addFlashAttribute("Error", "Otro cliente contiene este usuario");
               // model.addAttribute("UsuariosIgual", "Error");
                return "redirect:/agregados";
            }

        }catch (Exception e){


        }
        if (cliente.getDireccion().equals("") ||
                cliente.getId_persona().getNombre().equals("") ||
                cliente.getId_persona().getNombre().equals("") ||
                usuario.getUsuario().equals("") ||
                usuario.getPassword().equals("")) {
            ObjectMapper objectMapper = new ObjectMapper();

            redirect.addFlashAttribute("Error", "Ingrese todo los datos");
            return "redirect:/agregados";

        }
        Personas per = new Personas();
        per.setNombre(cliente.getId_persona().getNombre());
        per.setApellido(cliente.getId_persona().getApellido());
        per.setEstado(true);
        personaRepositorio.save(per);
        Personas per1 = personaRepositorio.findAll(Sort.by("id").descending()).get(0);
        usuario.setId_persona(per1);
        usuarioRepositorio.save(usuario);
        cliente.setId_persona(per1);
        cliente.setEstado(true);
        clienteRepositorio.save(cliente);
        Date fecha = new Date();
        for (CarritoDao x : carritoDao) {
            Pedidos pedido = new Pedidos();
            Productos productos;
            productos = repositorio.buscar(x.getIdproducto());
            pedido.setEstado("Encargado");
            pedido.setId_producto(productos);
            pedido.setId_persona(cliente);
            pedido.setCantidad(x.getCantidad());
            pedido.setFecha(obtenerFechaYHoraActual());
            pedidosRepositorio.save(pedido);

        }
        List<Pedidos> pedidos;
        pedidos = pedidosRepositorio.pedidosEnProceso(usuario.getId_persona().getId());
        carritoDao.clear();
        Double total = pedidosRepositorio.pedidosEnProcesoTotal(usuario.getId_persona().getId());
        model.addAttribute("total", total);
        pedidos = pedidosRepositorio.pedidosEnProceso(usuario.getId_persona().getId());
        model.addAttribute("pedidos", pedidos);//enviando la lista

        return "pedidos";
    }

    @GetMapping("/agregados")
    public String agregados(@RequestParam Map<String, Object> params, Model model, Productos producto) {


        List<Productos> producto1 = new ArrayList<>();
        Productos productos = new Productos();
        List<CarritoDao> carro = carritoDao;
        for (CarritoDao x : carritoDao) {

            producto1.add(repositorio.findAllById(Collections.singleton(x.getIdproducto())).get(0));
        }
        model.addAttribute("carritos", carro);//enviando la lista
        model.addAttribute("productos", producto1);//enviando la lista
        return "carrito";
    }

    @PostMapping("/pedido")
    public String pedido(@RequestParam Map<String, Object> params, Model model, Usuarios usuario, RedirectAttributes redirect) {
        List<Pedidos> pedidos;
        Usuarios usuarios;
        Double total = 0.00;
        try {


            usuarios = usuarioRepositorio.buscarUsuario(usuario.getUsuario());
             total = pedidosRepositorio.pedidosEnProcesoTotal(usuarios.getId_persona().getId());

            pedidos = pedidosRepositorio.pedidosEnProceso(usuarios.getId_persona().getId());
        } catch (Exception e) {
            pedidos = pedidosRepositorio.pedidosEnProceso(0);
            model.addAttribute("total", total);

            model.addAttribute("pedidos", pedidos);//enviando la lista
            return "pedidos";
        }
        model.addAttribute("total", total);

        model.addAttribute("pedidos", pedidos);//enviando la lista

        return "pedidos";
    }

    @GetMapping("/ecommerce")
    public String ecommerce(@RequestParam Map<String, Object> params, Model model, Productos producto) {
        List<Productos> producto1 = new ArrayList<>();
        try {
            producto1 = repositorio.findAll();
        } catch (Exception e) {
            return "ecommerce";
        }
        List<CarritoDao> carro = carritoDao;
        model.addAttribute("productos", producto1);//enviando la lista
        model.addAttribute("carritos", carro);//enviando la lista
        return "ecommerce";
    }

    @PostMapping("/AgregarPedido")
    public String agregraPedido(@RequestParam Map<String, Object> params, Model model, Usuarios usuario, RedirectAttributes redirect) {
        Usuarios usuarios;
        List<Pedidos> pedidos;

        try {
            usuarios = usuarioRepositorio.buscarUsuario(usuario.getUsuario());
            pedidos = pedidosRepositorio.pedidosEnProceso(usuarios.getId_persona().getId());
            if (usuarios.getPassword().equals(usuario.getPassword())) {
                Date fecha = new Date();
                for (CarritoDao x : carritoDao) {
                    Pedidos pedido = new Pedidos();
                    Productos productos;
                    productos = repositorio.buscar(x.getIdproducto());

                    Clientes clientes;

                    clientes = clienteRepositorio.buscarCliente(usuarios.getId_persona().getId());
                    pedido.setEstado("Encargado");
                    pedido.setId_producto(productos);
                    pedido.setId_persona(clientes);
                    pedido.setCantidad(x.getCantidad());
                    pedido.setFecha(obtenerFechaYHoraActual());
                    pedidosRepositorio.save(pedido);

                }
                carritoDao.clear();
                Double total = pedidosRepositorio.pedidosEnProcesoTotal(usuarios.getId_persona().getId());
                model.addAttribute("total", total);

                pedidos = pedidosRepositorio.pedidosEnProceso(usuarios.getId_persona().getId());
                model.addAttribute("pedidos", pedidos);//enviando la lista

                return "pedidos";
            } else {
                redirect.addFlashAttribute("Error", "Datos ingresados incorrectos");

                return "redirect:/agregados";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("Error", "Datos ingresados incorrectos");

            return "redirect:/agregados";
        }


    }

    public static String obtenerFechaYHoraActual() {
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }

    @PostMapping("/{id}/carrito")
    public String carrito(@PathVariable Integer id, @Validated Productos productos, BindingResult bindingResult, RedirectAttributes redirect, Model model) {
        CarritoDao carritoDao1 = new CarritoDao();
        int index = 0;
        for (CarritoDao x : carritoDao) {
            index++;
            if (x.getIdproducto() == id) {
                if ((x.getCantidad() + productos.getCantidad()) > 0) {
                    x.setCantidad(x.getCantidad() + productos.getCantidad());
                    return "redirect:/ecommerce";
                } else {
                    carritoDao.remove(index - 1);
                    return "redirect:/ecommerce";
                }

            }


        }
        index = 0;
        if (productos.getCantidad() > 0) {
            carritoDao1.setIdproducto(id);
            carritoDao1.setCantidad(productos.getCantidad());
            carritoDao.add(carritoDao1);
        }


        return "redirect:/ecommerce";
    }
}
