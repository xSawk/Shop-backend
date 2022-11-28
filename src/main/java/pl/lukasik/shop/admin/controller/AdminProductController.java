package pl.lukasik.shop.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.admin.controller.dto.AdminProductDto;
import pl.lukasik.shop.admin.model.AdminProduct;
import pl.lukasik.shop.admin.service.AdminProductService;

@RestController
public class AdminProductController {

    final private AdminProductService adminProductService;

    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @GetMapping("/admin/products")
    public Page<AdminProduct> getProducts(Pageable pageable){
        return adminProductService.getProducts(pageable);
    }


    @GetMapping("/admin/product/{id}")
    public AdminProduct getProduct(@PathVariable Long id){
        return adminProductService.getProduct(id);
    }

    @PostMapping("/admin/product")
    public AdminProduct addProduct(@RequestBody AdminProductDto adminProductDto){
        return adminProductService.addProduct(AdminProduct.builder()
                .name(adminProductDto.getName())
                .category(adminProductDto.getCategory())
                .description(adminProductDto.getDescription())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency())
                .build()
        );
    }
    @PutMapping("/admin/product/{id}")
    public AdminProduct updateProduct(@RequestBody AdminProductDto adminProductDto, @PathVariable Long id){
        return adminProductService.addProduct(AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .category(adminProductDto.getCategory())
                .description(adminProductDto.getDescription())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency().toUpperCase())
                .build()
        );
    }






}
