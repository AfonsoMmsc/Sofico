package com.sofico_backend.sofico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofico_backend.sofico.models.Brand;
import com.sofico_backend.sofico.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> getBrandByName(String name) {
        return brandRepository.findByName(name);
    }

    public List<Brand> getBrandByNames(List<String> names) {
        return brandRepository.findByNameIn(names);
    }

    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> addBrands(List<Brand> brands) {
        return brandRepository.saveAll(brands);
    }

    public Brand updateBrand(Long id, Brand updatedBrand) {
        return brandRepository.findById(id)
                .map(existingBrand -> {
                    existingBrand.setName(updatedBrand.getName());
                    return brandRepository.save(existingBrand);
                })
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    public void deleteBrand(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brand not found with id: " + id);
        }
    }
}