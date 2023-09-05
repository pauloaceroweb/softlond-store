package com.pauloaceroweb.softlondstore.service.impl;

import com.pauloaceroweb.softlondstore.entities.*;
import com.pauloaceroweb.softlondstore.repositories.ConfigDescuentoRepository;
import com.pauloaceroweb.softlondstore.repositories.ProductoRepository;
import com.pauloaceroweb.softlondstore.repositories.VentaDetalleRepository;
import com.pauloaceroweb.softlondstore.repositories.VentaRepository;
import com.pauloaceroweb.softlondstore.service.contracts.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ConfigDescuentoRepository configDescuentoRepository;

    @Override
    public List<Venta> findAll() {

        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    public Venta save(Venta venta) {
        BigDecimal totalVenta = BigDecimal.ZERO;

        venta.setDate(LocalDate.now());
        
        Venta ventaGuardada = ventaRepository.save(venta);

        for (VentaDetalle detalle : ventaGuardada.getVentaDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId()).orElse(null);
            if (producto != null) {
                // Obtener el precio actual del producto
                BigDecimal precioActual = producto.getPrice();

                // Registrar el precio de venta en el detalle
                detalle.setPrecioVenta(precioActual);

                // Calcular el total del producto
                BigDecimal totalProducto = precioActual.multiply(BigDecimal.valueOf(detalle.getCantidad()));
                detalle.setTotalProducto(totalProducto);

                // Actualizar el total de la venta
                totalVenta = totalVenta.add(totalProducto);

                detalle.setVenta(ventaGuardada);
                ventaDetalleRepository.save(detalle);
            }
        }

        if (clienteElegibleDescuento(venta)) {
            BigDecimal descuentoVenta = calcularDescuento(totalVenta);
            venta.setDescuentoVenta(descuentoVenta);
        }

        // Se aplica el descuento al total de la venta
        BigDecimal descuento = venta.getDescuentoVenta() != null ? venta.getDescuentoVenta() : BigDecimal.ZERO;
        totalVenta = totalVenta.subtract(descuento);
        ventaGuardada.setTotalVenta(totalVenta);

        return ventaRepository.save(ventaGuardada);
    }

    /*private BigDecimal aplicarDescuentoSorteo(BigDecimal totalVenta) {

    }*/

    private boolean clienteElegibleDescuento(Venta venta) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.minusDays(31);

        Cliente cliente = venta.getCliente();

        if (cliente != null) {
            BigDecimal totalCompras = ventaRepository.getTotalComprasCliente(
                    cliente.getId(),
                    fechaLimite,
                    fechaActual
            );
            BigDecimal valorBaseDescuento = getValorBaseDescuento();
            if (totalCompras != null && valorBaseDescuento != null) {
                return totalCompras.compareTo(valorBaseDescuento) >= 0;
            }
        }
        return false;
    }

    private BigDecimal getValorBaseDescuento() {
        Optional<ConfigDescuento> configDescuentoOptional = configDescuentoRepository.findById(1L);
        if (configDescuentoOptional.isPresent()) {
            return configDescuentoOptional.get().getValorBaseDescuento();
        } else {
            return BigDecimal.ZERO;
        }
    }

    private Integer getDescuentoVentasCompania() {
        Optional<ConfigDescuento> configDescuentoOptional = configDescuentoRepository.findById(1L);
        if (configDescuentoOptional.isPresent()) {
            return configDescuentoOptional.get().getDescuentoVenta();
        } else {
            return 0;
        }
    }

    private BigDecimal calcularDescuento(BigDecimal totalVenta) {
        if(totalVenta == null) {
            return BigDecimal.ZERO;
        }
        Integer descuentoEntero = getDescuentoVentasCompania();

        BigDecimal descuentoVenta = BigDecimal.valueOf(descuentoEntero).divide(BigDecimal.valueOf(100));

        return totalVenta.multiply(descuentoVenta);
    }
}
