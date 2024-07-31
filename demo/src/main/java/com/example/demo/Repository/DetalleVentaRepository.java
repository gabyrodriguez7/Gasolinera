package com.example.demo.Repository;

import com.example.demo.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    @Query("SELECT dv.cantidad FROM DetalleVenta dv WHERE dv.producto.id = :productoId")
    List<Integer> findCantidadByProductoId(@Param("productoId") Long productoId);
}
