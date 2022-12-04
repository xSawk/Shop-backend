package pl.lukasik.shop.admin.category.service;


import org.springframework.stereotype.Service;
import pl.lukasik.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.lukasik.shop.admin.category.model.AdminCategory;
import pl.lukasik.shop.admin.category.repository.AdminCategoryRepository;

import java.util.List;

@Service
public class AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    public AdminCategoryService(AdminCategoryRepository adminCategoryRepository) {
        this.adminCategoryRepository = adminCategoryRepository;
    }

    public List<AdminCategory> getCategories() {
        return adminCategoryRepository.findAll();

    }

    public AdminCategory getCategory(Long id) {
        return adminCategoryRepository.findById(id).orElseThrow();
    }


    public AdminCategory addCategory(AdminCategory adminCategory) {
        return adminCategoryRepository.save(adminCategory);
    }

    public AdminCategory updateCategory(AdminCategory adminCategory) {
        return adminCategoryRepository.save(adminCategory);
    }

    public void deleteCategory(Long id) {
        adminCategoryRepository.deleteById(id);
    }
}
