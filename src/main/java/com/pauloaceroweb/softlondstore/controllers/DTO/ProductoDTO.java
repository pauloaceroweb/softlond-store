package com.pauloaceroweb.softlondstore.controllers.DTO;

import com.pauloaceroweb.softlondstore.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Categoria categoria;
}
