package com.pauloaceroweb.softlondstore.service.impl;

import com.pauloaceroweb.softlondstore.entities.ConfigDescuento;
import com.pauloaceroweb.softlondstore.repositories.ConfigDescuentoRepository;
import com.pauloaceroweb.softlondstore.service.contracts.IConfigDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigDescuentoServiceImpl implements IConfigDescuentoService {

    @Autowired
    private ConfigDescuentoRepository configDescuentoRepository;

    @Override
    public Optional<ConfigDescuento> findById(Long id) {
        return configDescuentoRepository.findById(id);
    }

    @Override
    public void save(ConfigDescuento configDescuento) {
        configDescuentoRepository.save(configDescuento);
    }
}
