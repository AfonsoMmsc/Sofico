package com.sofico_backend.sofico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sofico_backend.sofico.models.Brand;
import com.sofico_backend.sofico.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public ResponseEntity<Brand> getBrandByName(@RequestParam String name) {
        return brandService.getBrandByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/names")
    public ResponseEntity<List<Brand>> getBrandByNames(@RequestParam List<String> names) {
        return ResponseEntity.ok(brandService.getBrandByNames(names));
    }

    @PostMapping
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.addBrand(brand));
    }

    @PostMapping("/addMany")
    public ResponseEntity<List<Brand>> addBrands(@RequestBody List<Brand> brands) {
        return ResponseEntity.ok(brandService.addBrands(brands));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand updatedBrand) {
        try {
            return ResponseEntity.ok(brandService.updateBrand(id, updatedBrand));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
