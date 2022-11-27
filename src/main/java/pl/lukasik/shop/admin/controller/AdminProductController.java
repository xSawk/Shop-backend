package pl.lukasik.shop.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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



}
