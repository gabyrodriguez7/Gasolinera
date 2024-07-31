package com.example.demo.Service;

import com.example.demo.Model.DetalleVenta;
import com.example.demo.Repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> findById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public DetalleVenta save(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }
    public List<Integer> getCantidadByProductoId(Long productoId) {
        return detalleVentaRepository.findCantidadByProductoId(productoId);
    }

    public void deleteById(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}