package com.pauloaceroweb.softlondstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "config_descuento")
public class ConfigDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_base_descuento")
    private BigDecimal valorBaseDescuento;

    @Column(name = "descuento_venta")
    private Integer descuentoVenta;

    @Column(name = "descuento_sorteo")
    private Integer descuentoSorteo;

}
