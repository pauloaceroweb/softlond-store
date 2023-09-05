package com.pauloaceroweb.softlondstore.controllers.DTO;

import com.pauloaceroweb.softlondstore.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Set<String> roles;
}
