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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {
    @Autowired
    private WasteCategoryRepository repository;

    public List<WasteCategory> getAllCategories() {
        return repository.findAll();
    }

    public Optional<WasteCategory> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public WasteCategory saveCategory(WasteCategory category) {
        return repository.save(category);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}