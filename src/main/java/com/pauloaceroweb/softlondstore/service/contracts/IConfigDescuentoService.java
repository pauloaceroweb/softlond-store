package com.pauloaceroweb.softlondstore.service.contracts;

import com.pauloaceroweb.softlondstore.entities.ConfigDescuento;

import java.util.Optional;

public interface IConfigDescuentoService {

    Optional<ConfigDescuento> findById(Long id);

    void save(ConfigDescuento configDescuento);

}
