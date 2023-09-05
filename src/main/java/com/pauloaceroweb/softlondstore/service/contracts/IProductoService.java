package com.pauloaceroweb.softlondstore.service.contracts;

import com.pauloaceroweb.softlondstore.entities.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> findAll();

    Optional<Producto> findById(Long id);

    List<Producto> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);

    void save(Producto producto);

    void deleteById(Long id);
}
