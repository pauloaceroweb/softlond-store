package com.pauloaceroweb.softlondstore.repositories;

import com.pauloaceroweb.softlondstore.entities.VentaDetalle;
import com.pauloaceroweb.softlondstore.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {

}
