package com.pauloaceroweb.softlondstore.service.contracts;

import com.pauloaceroweb.softlondstore.entities.VentaDetalle;

import java.util.List;
import java.util.Optional;

public interface IVentaDetalleService {

    Optional<VentaDetalle> findById(Long id);
}
