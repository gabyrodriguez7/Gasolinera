package com.example.demo.Service;

import com.example.demo.Model.Cliente;
import com.example.demo.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public Optional<Cliente> findByRucCedula(String rucCedula) {
        return clienteRepository.findByRucCedula(rucCedula);
    }
    public Optional<Cliente> findByPlaca(String rucCedula) {
        return clienteRepository.findByPlaca(rucCedula);
    }
}
