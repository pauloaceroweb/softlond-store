package com.pauloaceroweb.softlondstore.persistence.contracts;

import com.pauloaceroweb.softlondstore.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryDAO {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    void  save(Category category);

    void deleteById(Long id);
}
