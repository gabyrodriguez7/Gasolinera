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