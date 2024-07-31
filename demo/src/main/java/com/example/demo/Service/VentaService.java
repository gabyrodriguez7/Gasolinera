package com.example.demo.Service;


import com.example.demo.Model.*;
import com.example.demo.Repository.DetalleVentaRepository;
import com.example.demo.Repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    public void registrarVenta(List<String> nombresProductos, double total, String metodoPago, Cliente cliente, Usuario usuario) {
        // Crear una nueva instancia de Venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now()); // Fecha actual
        venta.setUsuario(usuario);

        // Guardar la venta en la base de datos
        Venta ventaGuardada = ventaRepository.save(venta);

        // Registrar los detalles de la venta
        for (String nombreProducto : nombresProductos) {
            Producto producto = productoService.findProductoByName(nombreProducto);
            if (producto != null) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setVenta(ventaGuardada);
                detalleVenta.setProducto(producto);
                detalleVenta.setCantidad(1); // Suponiendo que se vende una unidad por nombre de producto
                detalleVentaService.save(detalleVenta);
            }
        }
    }

    public double getTotalVentasByUsuarioId(Long usuarioId) {
        List<Venta> ventas = ventaRepository.findByUsuarioId(usuarioId);
        return ventas.stream().mapToDouble(Venta::getTotal).sum();
    }
    @Transactional
    public long registrarVenta(Venta venta) {
        Venta ventaGuardada = ventaRepository.save(venta);
        return ventaGuardada.getId(); // Devuelve el ID generado de la venta
    }

    @Transactional
    public void registrarDetalleVenta(DetalleVenta detalleVenta) {
        detalleVentaRepository.save(detalleVenta);
    }
}