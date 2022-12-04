package pl.lukasik.shop.admin.category.controller;


import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.lukasik.shop.admin.category.model.AdminCategory;
import pl.lukasik.shop.admin.category.service.AdminCategoryService;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    public AdminCategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @GetMapping
    public List<AdminCategory> getCategories(){
        return adminCategoryService.getCategories();


    }

    @GetMapping("/{id}")
    public AdminCategory getCategory(@PathVariable Long id) {
        return adminCategoryService.getCategory(id);

    }

    @PostMapping
    public AdminCategory addCategory(@RequestBody AdminCategoryDto adminCategoryDto){
        return adminCategoryService.addCategory(AdminCategory.builder()
                .name(adminCategoryDto.getName())
                .description(adminCategoryDto.getDescription())
                .build()

        );
    }

    @PutMapping("/{id}")
    public AdminCategory updateCategory(@RequestBody AdminCategoryDto adminCategoryDto, @PathVariable Long id){
        return adminCategoryService.updateCategory(AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.getName())
                .description(adminCategoryDto.getDescription())
                .build()
        );
    }



    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
            adminCategoryService.deleteCategory(id);
    }
}
