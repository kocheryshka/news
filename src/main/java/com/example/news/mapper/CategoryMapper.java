package com.example.news.mapper;

import com.example.news.model.Category;
import com.example.news.web.model.list.CategoryListResponse;
import com.example.news.web.model.single.CategoryResponse;
import com.example.news.web.model.upsert.UpsertCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

//ToDo uses!!!!
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE/*, uses = !!!!!!!!!!*/)
public interface CategoryMapper {

    CategoryResponse categoryToResponse(Category category);

    Category requestToCategory(UpsertCategoryRequest request);

    Category requestToCategory(Long categoryId, UpsertCategoryRequest request);


    default CategoryListResponse categoryListToCategoryListResponse(List<Category> categories) {
        CategoryListResponse categoryListResponse = new CategoryListResponse();
        categoryListResponse.setCategories(
                categories.stream()
                        .map(this::categoryToResponse)
                        .toList()
        );
        return categoryListResponse;
    }


}
