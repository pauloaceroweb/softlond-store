package com.pauloaceroweb.softlondstore.controllers.DTO;

import com.pauloaceroweb.softlondstore.entities.Producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDTO {

    private Long id;
    private String name;
    private List<Producto> productoList = new ArrayList<>();
}
