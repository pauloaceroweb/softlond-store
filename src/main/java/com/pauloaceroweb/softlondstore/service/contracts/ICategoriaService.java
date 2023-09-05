package com.pauloaceroweb.softlondstore.service.contracts;

import com.pauloaceroweb.softlondstore.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    List<Categoria> findAll();

    Optional<Categoria> findById(Long id);

    void save(Categoria categoria);

    void deleteById(Long id);
}
