package com.sofico_backend.sofico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofico_backend.sofico.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentCategoryId(Long parentId);
}