package com.pauloaceroweb.softlondstore.service.contracts;


import com.pauloaceroweb.softlondstore.entities.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> findAll();

    Optional<Venta> findById(Long id);

    Venta save(Venta venta);

    void deleteById(Long id);

}
