package com.pauloaceroweb.softlondstore.controllers;

import com.pauloaceroweb.softlondstore.controllers.DTO.ProductoDTO;
import com.pauloaceroweb.softlondstore.entities.Categoria;
import com.pauloaceroweb.softlondstore.entities.Producto;
import com.pauloaceroweb.softlondstore.service.contracts.ICategoriaService;
import com.pauloaceroweb.softlondstore.service.contracts.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private IProductoService productService;

    private ICategoriaService categoriaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Producto> productOptional = productService.findById(id);

        if (productOptional.isPresent()) {
            Producto producto = productOptional.get();

            ProductoDTO productoDTO = ProductoDTO.builder()
                    .id(producto.getId())
                    .name(producto.getName())
                    .price(producto.getPrice())
                    .categoria(producto.getCategoria())
                    .build();

            return ResponseEntity.ok(productoDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ProductoDTO> productList = productService.findAll()
                .stream()
                .map(product -> ProductoDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .categoria(product.getCategoria())
                        .build())
                .toList();

        return ResponseEntity.ok(productList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductoDTO productoDTO) throws URISyntaxException {
        if(productoDTO.getName().isBlank() || productoDTO.getPrice() == null || productoDTO.getCategoria() == null) {
            return ResponseEntity.badRequest().build();
        }

        Producto producto = Producto.builder()
                .name(productoDTO.getName())
                .price(productoDTO.getPrice())
                .categoria(productoDTO.getCategoria())
                .build();

        productService.save(producto);

        return ResponseEntity.created(new URI("/product/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        Optional<Producto> productOptional = productService.findById(id);
        Optional<Categoria> categoriaOptional = categoriaService.findById(productoDTO.getCategoria().getId());

        if (productOptional.isPresent()) {
            Producto producto = productOptional.get();
            Categoria categoria = categoriaOptional.get();

            producto.setName(productoDTO.getName());
            producto.setPrice(productoDTO.getPrice());
            producto.setCategoria(categoria);

            productService.save(producto);
            return ResponseEntity.ok("Registro Actualizado Correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByPriceRange")
    public ResponseEntity<?> findByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {

        if (minPrice == null || maxPrice == null) {
            return ResponseEntity.badRequest().build();
        }

        List<ProductoDTO> productList = productService.findByPriceInRange(minPrice, maxPrice)
                .stream()
                .map(product -> ProductoDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .categoria(product.getCategoria())
                        .build())
                .toList();

        return ResponseEntity.ok(productList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if(id != null) {
            productService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado Correctamente");
        }

        return ResponseEntity.badRequest().build();
    }
}
