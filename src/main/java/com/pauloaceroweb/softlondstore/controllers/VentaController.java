package com.pauloaceroweb.softlondstore.controllers;

import com.pauloaceroweb.softlondstore.controllers.DTO.VentaDTO;
import com.pauloaceroweb.softlondstore.entities.Venta;
import com.pauloaceroweb.softlondstore.service.contracts.IVentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @PostMapping("/save")
    public ResponseEntity<?> crearVenta(@RequestBody VentaDTO ventaDTO) {
        try {

            log.info("Datos de ventaDTO: {}", ventaDTO);

            if (ventaDTO.getCliente() == null || ventaDTO.getVentaDetalles() == null) {
                return ResponseEntity.badRequest().build();
            }

            Venta venta = Venta.builder()
                    .cliente(ventaDTO.getCliente())
                    .ventaDetalles(ventaDTO.getVentaDetalles())
                    .build();


            ventaService.save(venta);

            return ResponseEntity.created(new URI("/ventas/save")).build();
        } catch (Exception e) {
            log.error("Error al crear la venta", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la venta");
        }
    }

}

