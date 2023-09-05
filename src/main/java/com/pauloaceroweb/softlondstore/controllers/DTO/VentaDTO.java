package com.pauloaceroweb.softlondstore.controllers.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pauloaceroweb.softlondstore.entities.Cliente;
import com.pauloaceroweb.softlondstore.entities.VentaDetalle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private BigDecimal totalVenta;
    private Cliente cliente;
    private List<VentaDetalle> ventaDetalles = new ArrayList<>();
}
