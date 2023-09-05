package com.pauloaceroweb.softlondstore.repositories;

import com.pauloaceroweb.softlondstore.entities.ConfigDescuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigDescuentoRepository extends JpaRepository<ConfigDescuento, Long> {
}
