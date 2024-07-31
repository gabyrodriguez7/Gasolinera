package com.example.demo.Repository;

import com.example.demo.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByPlaca(String placa);
    Optional<Cliente> findByRucCedula(String rucCedula);

}
