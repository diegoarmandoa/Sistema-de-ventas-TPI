package TPI.TPI.Controller;

import TPI.TPI.Entity.*;
import TPI.TPI.Enumeraciones.EstadoPedidos;
import TPI.TPI.Repository.*;
import TPI.TPI.service.api.VentaServiceAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    VentaServiceAPI ventaServiceAPI;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
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
           /* if (passwordEncoder.matches(usuario.getPassword(), passwordEncoder.encode(usuario.getPassword()))) {
                redirect.addFlashAttribute("Error", "Otro cliente contiene este usuario");
                // model.addAttribute("UsuariosIgual", "Error");
                return "redirect:/agregados";
            } else */
            if (usuarioRepositorio.buscarUsuario(usuario.getUsuario()).getId_Usuario().equals("")) {
                redirect.addFlashAttribute("Error", "Otro cliente contiene este usuario");
                // model.addAttribute("UsuariosIgual", "Error");
                return "redirect:/agregados";
            }


        } catch (Exception e) {


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
        usuario.setPersona(per1);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepositorio.save(usuario);
        cliente.setId_persona(per1);
        cliente.setEstado(true);
        clienteRepositorio.save(cliente);
        Date fecha = new Date();

        Ventas venta = new Ventas();
        ArrayList<Pedidos> pedidosVenta = new ArrayList<>();
        float sumaTotal = 0;
        Integer idGenerado = ventaServiceAPI.obtenerUltimoID() + 1;
        venta.setId(idGenerado);
        for (CarritoDao x : carritoDao) {
            Pedidos pedido = new Pedidos();
            Productos productos;
            productos = repositorio.buscar(x.getIdproducto());
            pedido.setEstadoPedidos(EstadoPedidos.PREPARACION);
            pedido.setId_producto(productos);
            pedido.setId_persona(cliente);
            pedido.setVenta(venta);
            pedido.setCantidad(x.getCantidad());
            pedidosVenta.add(pedido);


            sumaTotal += (x.getCantidad() * productos.getPrecio());
        }

        venta.setPedidos(pedidosVenta);
        venta.setTotal(sumaTotal);
        venta.setFecha(LocalDateTime.now());
        venta.setCliente(cliente);

        ventaServiceAPI.save(venta);

        model.addAttribute("idCliente", cliente.getId_cliente());
        return "mapa";
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
        List<Pedidos> pedidos = null;
        Usuarios usuarios;
        Double total = 0.00;
        try {

            if (passwordEncoder.matches(usuario.getPassword(), passwordEncoder.encode(usuario.getPassword()))) {
                usuarios = usuarioRepositorio.buscarUsuario(usuario.getUsuario());
                total = pedidosRepositorio.pedidosEnProcesoTotal(usuarios.getPersona().getId(), EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
                pedidos = pedidosRepositorio.pedidosEnProceso(usuarios.getPersona().getId(), EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);

            }

        } catch (Exception e) {
            pedidos = pedidosRepositorio.pedidosEnProceso(0, EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
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

    @GetMapping("/mapa")
    public String mapa(Model model, @RequestParam("id") Integer id,
                       @RequestParam("Latitud") Double latitud, @RequestParam("Longitud") Double longitud) {
        clienteRepositorio.updateLatitudYLongitud(latitud, longitud, id);

        List<Pedidos> pedidos;
        pedidos = pedidosRepositorio.pedidosEnProceso(id, EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
        carritoDao.clear();
        Double total = pedidosRepositorio.pedidosEnProcesoTotal(id, EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
        model.addAttribute("total", total);
        pedidos = pedidosRepositorio.pedidosEnProceso(id, EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
        model.addAttribute("pedidos", pedidos);//enviando la lista

        return "pedidos";

    }

    @PostMapping("/AgregarPedido")
    public String agregraPedido(@RequestParam Map<String, Object> params, Model model, Usuarios usuario, RedirectAttributes redirect) {
        Usuarios usuarios;
        List<Pedidos> pedidos;

        try {
            usuarios = usuarioRepositorio.buscarUsuario(usuario.getUsuario());
            pedidos = pedidosRepositorio.pedidosEnProceso(usuarios.getPersona().getId(), EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);

            if (passwordEncoder.matches(usuario.getPassword(), passwordEncoder.encode(usuario.getPassword()))) {
                Date fecha = new Date();
                Ventas venta = new Ventas();
                ArrayList<Pedidos> pedidosVenta = new ArrayList<>();
                float SumaTotal = 0;
                Integer idGenerado = ventaServiceAPI.obtenerUltimoID() + 1;
                venta.setId(idGenerado);
                Clientes clientes;

                clientes = clienteRepositorio.buscarCliente(usuarios.getPersona().getId());

                for (CarritoDao x : carritoDao) {


                    Pedidos pedido = new Pedidos();
                    Productos productos;
                    productos = repositorio.buscar(x.getIdproducto());
                    pedido.setEstadoPedidos(EstadoPedidos.PREPARACION);
                    pedido.setId_producto(productos);
                    pedido.setId_persona(clientes);
                    pedido.setCantidad(x.getCantidad());

                    SumaTotal += (x.getCantidad() * productos.getPrecio());
                    pedido.setVenta(venta);
                    pedidosVenta.add(pedido);

                }
                venta.setPedidos(pedidosVenta);
                venta.setTotal(SumaTotal);
                venta.setFecha(LocalDateTime.now());
                venta.setCliente(clientes);

                ventaServiceAPI.save(venta);

                carritoDao.clear();

                Double total = pedidosRepositorio.pedidosEnProcesoTotal(usuarios.getPersona().getId(), EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
                model.addAttribute("total", total);

                pedidos = pedidosRepositorio.pedidosEnProceso(usuarios.getPersona().getId(), EstadoPedidos.LISTO, EstadoPedidos.PREPARACION);
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
