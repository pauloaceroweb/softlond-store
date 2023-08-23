package com.pauloaceroweb.softlondstore.persistence.impl;

import com.pauloaceroweb.softlondstore.entities.Category;
import com.pauloaceroweb.softlondstore.persistence.contracts.ICategoryDAO;
import com.pauloaceroweb.softlondstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryDAOImpl implements ICategoryDAO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
