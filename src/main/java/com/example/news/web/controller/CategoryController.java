package com.example.news.web.controller;

import com.example.news.mapper.CategoryMapper;
import com.example.news.model.Category;
import com.example.news.service.CategoryService;
import com.example.news.utils.BeanUtils;
import com.example.news.web.model.list.CategoryListResponse;
import com.example.news.web.model.single.CategoryResponse;
import com.example.news.web.model.upsert.UpsertCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(){
        return ResponseEntity.ok(categoryMapper.categoryListToCategoryListResponse(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryMapper.categoryToResponse(categoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody UpsertCategoryRequest request){
        Category category = categoryService.save(categoryMapper.requestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.categoryToResponse(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody UpsertCategoryRequest request){
        Category existingCategory = categoryService.findById(id);
        Category newCategory = categoryMapper.requestToCategory(id, request);
        BeanUtils.copyNonNullProperties(newCategory, existingCategory);

        return ResponseEntity.ok(categoryMapper.categoryToResponse(categoryService.save(existingCategory)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
