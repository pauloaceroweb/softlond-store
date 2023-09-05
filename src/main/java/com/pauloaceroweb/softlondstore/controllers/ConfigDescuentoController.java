package com.pauloaceroweb.softlondstore.controllers;

import com.pauloaceroweb.softlondstore.controllers.DTO.ConfigDescuentoDTO;
import com.pauloaceroweb.softlondstore.entities.ConfigDescuento;
import com.pauloaceroweb.softlondstore.service.contracts.IConfigDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/config-descuentos")
public class ConfigDescuentoController {

    @Autowired
    private IConfigDescuentoService configDescuentoService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<ConfigDescuento> configDescuentoOptional = configDescuentoService.findById(id);

        if (configDescuentoOptional.isPresent()) {
            ConfigDescuento configDescuento = configDescuentoOptional.get();

            ConfigDescuentoDTO configDescuentoDTO = ConfigDescuentoDTO.builder()
                    .id(configDescuento.getId())
                    .valorBaseDescuento(configDescuento.getValorBaseDescuento())
                    .descuentoVenta(configDescuento.getDescuentoVenta())
                    .descuentoSorteo(configDescuento.getDescuentoSorteo())
                    .build();

            return ResponseEntity.ok(configDescuentoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ConfigDescuentoDTO configDescuentoDTO) throws URISyntaxException {
        if(configDescuentoDTO.getValorBaseDescuento() == null ||
                configDescuentoDTO.getDescuentoVenta() == null ||
                configDescuentoDTO.getDescuentoSorteo() == null) {
            return ResponseEntity.badRequest().build();
        }

        ConfigDescuento configDescuento = ConfigDescuento.builder()
                .valorBaseDescuento(configDescuentoDTO.getValorBaseDescuento())
                .descuentoVenta(configDescuentoDTO.getDescuentoVenta())
                .descuentoSorteo(configDescuentoDTO.getDescuentoSorteo())
                .build();

        configDescuentoService.save(configDescuento);

        return ResponseEntity.created(new URI("/config-descuentos/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ConfigDescuentoDTO configDescuentoDTO) {
        Optional<ConfigDescuento> configDescuentoOptional = configDescuentoService.findById(id);

        if (configDescuentoOptional.isPresent()) {
            ConfigDescuento configDescuento = configDescuentoOptional.get();

            configDescuento.setValorBaseDescuento(configDescuentoDTO.getValorBaseDescuento());
            configDescuento.setDescuentoVenta(configDescuentoDTO.getDescuentoVenta());
            configDescuento.setDescuentoSorteo(configDescuentoDTO.getDescuentoSorteo());

            configDescuentoService.save(configDescuento);
            return ResponseEntity.ok("Registro Actualizado Correctamente");
        }
        return ResponseEntity.notFound().build();
    }

}
