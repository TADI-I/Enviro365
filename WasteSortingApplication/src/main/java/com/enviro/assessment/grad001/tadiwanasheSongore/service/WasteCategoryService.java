/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enviro.assessment.grad001.tadiwanasheSongore.service;

/**
 *
 * @author tadiw
 */
import com.enviro.assessment.grad001.tadiwanasheSongore.model.WasteCategory;
import com.enviro.assessment.grad001.tadiwanasheSongore.repository.WasteCategoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    private final WasteCategoryRepository repository;

    @Autowired
    public WasteCategoryService(WasteCategoryRepository repository) {
        this.repository = repository;
    }

    public List<WasteCategory> getAllCategories() {
        return repository.findAll();
    }

    public WasteCategory getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    public WasteCategory createCategory(WasteCategory category) {
        Optional<WasteCategory> existingCategory = repository.findByName(category.getName());
        if (existingCategory.isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        return repository.save(category);
    }

    public void deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Category does not exist");
        }
        repository.deleteById(id);
    }

    @PostConstruct
    public void initData() {
        if (repository.count() == 0) {
            repository.save(new WasteCategory("Plastic"));
            repository.save(new WasteCategory("Metal"));
            repository.save(new WasteCategory("Glass"));
        }
    }
}
