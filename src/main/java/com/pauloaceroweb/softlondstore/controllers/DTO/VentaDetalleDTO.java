package com.pauloaceroweb.softlondstore.controllers.DTO;

import com.pauloaceroweb.softlondstore.entities.Producto;
import com.pauloaceroweb.softlondstore.entities.Venta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDetalleDTO {

    private Long id;
    private Integer cantidad;
    private Long descuento;
    private BigDecimal totalProducto;
    private Venta venta;
    private Producto producto;
}
