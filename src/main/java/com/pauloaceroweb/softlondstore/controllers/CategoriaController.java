package com.pauloaceroweb.softlondstore.controllers;

import com.pauloaceroweb.softlondstore.controllers.DTO.CategoriaDTO;
import com.pauloaceroweb.softlondstore.entities.Categoria;
import com.pauloaceroweb.softlondstore.service.contracts.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoryService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Categoria> categoryOptional = categoryService.findById(id);

        if (categoryOptional.isPresent()) {
            Categoria categoria = categoryOptional.get();

            CategoriaDTO categoriaDTO = CategoriaDTO.builder()
                    .id(categoria.getId())
                    .name(categoria.getName())
                    .productoList(categoria.getProductoList())
                    .build();

            return ResponseEntity.ok(categoriaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        List<CategoriaDTO> categoryList = categoryService.findAll()
                .stream()
                .map(category -> CategoriaDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .productoList(category.getProductoList())
                        .build())
                .toList();

        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoriaDTO categoriaDTO) throws URISyntaxException {
        if (categoriaDTO.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        categoryService.save(Categoria.builder()
                .name(categoriaDTO.getName())
                .build());

        return ResponseEntity.created(new URI("/category/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoryOptional = categoryService.findById(id);

        if (categoryOptional.isPresent()) {
            Categoria categoria = categoryOptional.get();
            categoria.setName(categoriaDTO.getName());
            categoryService.save(categoria);
            return ResponseEntity.ok("Registro Actualizado Correctamente");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (id != null) {
            categoryService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado Exitosamente");
        }

        return ResponseEntity.badRequest().build();
    }
}
