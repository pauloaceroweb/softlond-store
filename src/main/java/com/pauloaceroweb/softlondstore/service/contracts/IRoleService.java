package com.pauloaceroweb.softlondstore.service.contracts;

import com.pauloaceroweb.softlondstore.entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();

    Optional<Role> findById(Long id);

    void  save(Role role);

    void deleteById(Long id);
}
