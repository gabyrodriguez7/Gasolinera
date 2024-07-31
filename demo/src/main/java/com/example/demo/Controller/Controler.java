package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.*;
import com.example.demo.View.VistaEmpleado;
import com.example.demo.View.VistaJefe;
import com.example.demo.View.Window;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Component
public class Controler {
    private Usuario usuario;

    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final VentaService ventaService;
    private final UsuarioService usuarioService;
    private final DetalleVentaService detalleVentaService;


    @Autowired
    public Controler(ClienteService clienteService, ProductoService productoService, VentaService ventaService, UsuarioService usuarioService, DetalleVentaService detalleVentaService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
        this.detalleVentaService = detalleVentaService;

    }


    public Cliente guardar(Cliente cliente) {
        return clienteService.save(cliente);
    }

    public Optional<Cliente> buscarCliente(String placa) {
        return clienteService.findByPlaca(placa);
    }

    public void iniciarVistaSegunRol(String username, String password) {
        Optional<Usuario> optionalUsuario = usuarioService.findByUsernameAndPassword(username, password);

        if (optionalUsuario.isPresent()) {
            this.usuario = optionalUsuario.get();
            String rol = usuario.getRol();

            if ("empleado".equalsIgnoreCase(rol)) {
                iniciarVistaEmpleado();
            } else if ("jefe".equalsIgnoreCase(rol)) {
                iniciarVistaJefe();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            Window window = new Window(this);
            window.setVisible(true);
        }
    }

    public void iniciarVistaEmpleado() {
        VistaEmpleado vistaEmpleado = new VistaEmpleado(this);
        vistaEmpleado.setVisible(true);
    }

    public void iniciarVistaJefe() {
        VistaJefe vistaJefe = new VistaJefe(this);
        vistaJefe.setVisible(true);
    }

    public List<Producto> cargarDatosProductos() {
        return productoService.findAll();
    }

    public Producto findProductoByName(String nombre) {
        return productoService.findProductoByName(nombre);
    }

    public boolean realizarVenta(Cliente cliente, List<String> productosVendidos, String metodoPago) {

        double totalVenta = 0.0;

        // Crear una nueva instancia de Venta para esta transacción
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFecha(LocalDateTime.now());
        nuevaVenta.setMetodoPago(metodoPago);
        nuevaVenta.setCliente(cliente);
        nuevaVenta.setUsuario(usuario);
        ventaService.save(nuevaVenta);

        for (String productoVendido : productosVendidos) {
            String[] parts = productoVendido.split(" - ");
            String nombreProducto = parts[0];
            double cantidad = Double.parseDouble(parts[1]);

            Producto producto = findProductoByName(nombreProducto);
            if (producto != null) {

                DetalleVenta nuevoDetalleVenta = new DetalleVenta();
                nuevoDetalleVenta.setVenta(nuevaVenta);
                nuevoDetalleVenta.setProducto(producto);
                nuevoDetalleVenta.setCantidad((int) cantidad);
                nuevoDetalleVenta.setPrecioUnitario(producto.getPrecio());
                nuevoDetalleVenta.setSubtotal(nuevoDetalleVenta.getPrecioUnitario() * cantidad);
                detalleVentaService.save(nuevoDetalleVenta);

                totalVenta += nuevoDetalleVenta.getSubtotal();


                producto.setStock((int) (producto.getStock() - cantidad));
                productoService.save(producto);
            } else {
                return false; // Product not found
            }
        }


        nuevaVenta.setTotal(totalVenta);
        ventaService.save(nuevaVenta);

        return true;
    }

    public List<Integer> getCantidadByProductoId(Long productoId) {
        return detalleVentaService.getCantidadByProductoId(productoId);
    }

    public int someBusinessMethod(Long productoId) {
        List<Integer> cantidades = detalleVentaService.getCantidadByProductoId(productoId);
        return cantidades.stream().mapToInt(Integer::intValue).sum();
    }

    public String nameUser() {
        return usuario.getUsername();
    }

    public Producto findProductoById(Long id) {
        return productoService.findById(id).orElse(null);
    }

    public List<Producto> fingAllProducto() {
        return productoService.findAll();
    }

    public List<Usuario> fingAllUsuario() {
        return usuarioService.findAll();
    }

    public List<Usuario> buscarUsuariosPorRol(String rol) {
        return usuarioService.buscarPorRol(rol);
    }

    public double getTotalVentasByUsuarioId(Long usuarioId) {
        return ventaService.getTotalVentasByUsuarioId(usuarioId);
    }

    public void deleteUser(String username) {
        Optional<Usuario> Usuario = usuarioService.findByUsername(username);
        usuarioService.deleteById(Usuario.get().getId());
    }

    public void saveEmpleado(Usuario usuario) {
        usuarioService.save(usuario);
    }

    public void agregarProductos(Producto producto) {
        productoService.save(producto);
    }

    public void updateProducto(String nameP, int Strock) {
        productoService.actualizarProducto(nameP, Strock);
    }
    public void actualizarStock1(Producto producto, int nuevoStock) {
        Optional<Producto> optionalProducto = Optional.ofNullable(productoService.findProductoById(producto.getId()));

        if (optionalProducto.isPresent()) {
            Producto productoEncontrado = optionalProducto.get();
            productoEncontrado.setStock(nuevoStock);
            productoService.save(productoEncontrado);

            // Aquí podrías agregar lógica adicional si deseas hacer algo después de actualizar el stock

        } else {

            System.out.println("No se encontró un producto con ID: " + producto.getId());
        }
    }

    public boolean actualizarStock(Producto producto, int nuevoStock) {
        Optional<Producto> optionalProducto = Optional.ofNullable(productoService.findProductoById(producto.getId()));

        if (optionalProducto.isPresent()) {
            Producto productoEncontrado = optionalProducto.get();
            productoEncontrado.setStock(nuevoStock);
            productoService.save(productoEncontrado);
            return true;


        } else {

            System.out.println("No se encontró un producto con ID: " + producto.getId());
            return false;
        }
    }

}





