package com.pauloaceroweb.softlondstore.service.impl;

import com.pauloaceroweb.softlondstore.entities.Cliente;
import com.pauloaceroweb.softlondstore.repositories.ClienteRepository;
import com.pauloaceroweb.softlondstore.service.contracts.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> findByUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
