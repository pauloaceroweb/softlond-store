package com.pauloaceroweb.softlondstore.repositories;

import com.pauloaceroweb.softlondstore.entities.Venta;
import com.pauloaceroweb.softlondstore.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByDate(LocalDate date);

    List<Venta> findByCliente(Cliente cliente);

    List<Venta> findByClienteAndDateBetween(Cliente cliente, LocalDate fistDate, LocalDate lastDate);

    @Query("SELECT v FROM Venta v WHERE v.cliente.id = :clienteId AND v.date >= :fechaLimite")
    List<Venta> findVentasByClienteIdAndFechaGreaterThanEqual(@Param("clienteId") Long clienteId,
                                                              @Param("fechaLimite") LocalDate fechaLimite);

    @Query("SELECT DISTINCT v.cliente FROM Venta v WHERE v.date BETWEEN :fechaLimite AND :fechaActual")
    List<Cliente> findDistinctClienteByDateBetween(@Param("fechaLimite") LocalDate fechaLimite,
                                                   @Param("fechaActual") LocalDate fechaActual);

    @Query("SELECT SUM(vd.precioVenta) FROM VentaDetalle vd " +
            "JOIN vd.venta v " +
            "WHERE v.cliente.id = :cliente AND v.date BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal getTotalComprasCliente(@Param("cliente") Long cliente,
                                      @Param("fechaInicio") LocalDate fechaInicio,
                                      @Param("fechaFin") LocalDate fechaFin);


}
