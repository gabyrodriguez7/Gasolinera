package com.example.demo.Repository;

import com.example.demo.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT v FROM Venta v WHERE v.usuario.id = :usuarioId")
    List<Venta> findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
