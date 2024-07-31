package com.example.demo.Service;

import com.example.demo.Model.Producto;
import com.example.demo.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    private  ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
    public Producto findProductoByName(String nombre) {
        Optional<Producto> optionalProducto = productoRepository.findByNombre(nombre);
        return optionalProducto.orElse(null);
    }
    public void actualizarProducto(String nombre, Double nuevoStock) {
        Optional<Producto> optionalProducto = productoRepository.findByNombre(nombre);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setStock(nuevoStock);
            productoRepository.save(producto);
        } else {
            // Manejo de caso en que el producto no se encuentra
            throw new IllegalArgumentException("No se encontró ningún producto con el nombre especificado: " + nombre);
        }
    }
    public Producto findProductoById(Long id) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        return optionalProducto.orElse(null);
    }

}