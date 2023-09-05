package com.pauloaceroweb.softlondstore.repositories;

import com.pauloaceroweb.softlondstore.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

}
