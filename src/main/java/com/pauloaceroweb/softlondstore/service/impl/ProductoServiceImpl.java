package com.pauloaceroweb.softlondstore.service.impl;

import com.pauloaceroweb.softlondstore.entities.Producto;
import com.pauloaceroweb.softlondstore.repositories.ProductoRepository;
import com.pauloaceroweb.softlondstore.service.contracts.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productoRepository.findProductByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
