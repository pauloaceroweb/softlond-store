package com.pauloaceroweb.softlondstore.controllers;

import com.pauloaceroweb.softlondstore.controllers.DTO.ClienteDTO;
import com.pauloaceroweb.softlondstore.entities.Cliente;
import com.pauloaceroweb.softlondstore.entities.ERole;
import com.pauloaceroweb.softlondstore.entities.Role;
import com.pauloaceroweb.softlondstore.service.contracts.IClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteService.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            Set<String> roleNames = cliente.getRoles()
                    .stream()
                    .map(role -> role.getName().toString())
                    .collect(Collectors.toSet());

            ClienteDTO clienteDTO = ClienteDTO.builder()
                    .id(cliente.getId())
                    .name(cliente.getName())
                    .email(cliente.getEmail())
                    .username(cliente.getUsername())
                    .password(cliente.getPassword())
                    .roles(roleNames)
                    .build();

            return ResponseEntity.ok(clienteDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ClienteDTO> clienteList = clienteService.findAll()
                .stream()
                .map(cliente -> {
                    Set<String> roleNames = cliente.getRoles()
                            .stream()
                            .map(role -> role.getName().toString())
                            .collect(Collectors.toSet());

                    return ClienteDTO.builder()
                            .id(cliente.getId())
                            .name(cliente.getName())
                            .email(cliente.getEmail())
                            .username(cliente.getUsername())
                            .password(cliente.getPassword())
                            .roles(roleNames)
                            .build();
                })
                .toList();

        return ResponseEntity.ok(clienteList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody ClienteDTO clienteDTO) throws URISyntaxException {

        Set<Role> roles = clienteDTO.getRoles().stream()
                .map(roleName -> {
                    ERole eRole = ERole.valueOf(roleName);
                    return Role.builder().name(eRole).build();
                })
                .collect(Collectors.toSet());

        Cliente cliente = Cliente.builder()
                .name(clienteDTO.getName())
                .email(clienteDTO.getEmail())
                .username(clienteDTO.getUsername())
                .password(clienteDTO.getPassword())
                .roles(roles)
                .build();

        clienteService.save(cliente);

        return ResponseEntity.created(new URI("/clientes/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteService.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setName(clienteDTO.getName());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setUsername(clienteDTO.getUsername());

            Set<Role> roles = clienteDTO.getRoles().stream()
                    .map(roleName -> Role.builder()
                            .name(ERole.valueOf(roleName))
                            .build())
                    .collect(Collectors.toSet());

            cliente.setRoles(roles);  // Actualizar roles

            clienteService.save(cliente);
            return ResponseEntity.ok("Registro Actualizado Correctamente");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if(id != null) {
            clienteService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado Correctamente");
        }
        return ResponseEntity.badRequest().build();
    }
}
