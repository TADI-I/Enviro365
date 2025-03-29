/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enviro.assessment.grad001.tadiwanasheSongore.controller;

/**
 *
 * @author tadiw
 */
import com.enviro.assessment.grad001.tadiwanasheSongore.model.WasteCategory;
import com.enviro.assessment.grad001.tadiwanasheSongore.service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {

    private final WasteCategoryService service;

    public WasteCategoryController(WasteCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createCategory(@RequestBody WasteCategory category) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

