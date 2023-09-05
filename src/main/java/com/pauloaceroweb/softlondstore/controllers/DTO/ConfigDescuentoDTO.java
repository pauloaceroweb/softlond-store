package com.pauloaceroweb.softlondstore.controllers.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigDescuentoDTO {

    private Long id;
    private BigDecimal valorBaseDescuento;
    private Integer descuentoVenta;
    private Integer descuentoSorteo;
}
