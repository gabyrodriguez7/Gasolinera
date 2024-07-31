package com.example.demo.Repository;

import com.example.demo.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}
