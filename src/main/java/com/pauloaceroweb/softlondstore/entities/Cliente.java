package com.pauloaceroweb.softlondstore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre requerido")
    private String name;

    @Email
    @NotBlank
    @Size(max = 80, message = "Email requerido")
    private String email;

    @NotBlank
    @Size(max = 30, message = "Nombre de usuario requerido")
    private String username;

    @NotBlank
    @Size(min = 6, message = "Contraseña requerida con mínimo 6 caracteres")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles; //Set - no permite elementos duplicados

}
