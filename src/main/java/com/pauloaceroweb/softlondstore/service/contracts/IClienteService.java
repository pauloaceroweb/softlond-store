package com.pauloaceroweb.softlondstore.service.contracts;

import com.pauloaceroweb.softlondstore.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findByUsername(String username);

    void save(Cliente cliente);

    void deleteById(Long id);
}
