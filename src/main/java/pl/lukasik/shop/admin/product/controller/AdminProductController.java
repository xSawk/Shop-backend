package pl.lukasik.shop.admin.product.controller;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.lukasik.shop.admin.product.controller.dto.AdminProductDto;
import pl.lukasik.shop.admin.product.controller.dto.UploadResponse;
import pl.lukasik.shop.admin.product.model.AdminProduct;
import pl.lukasik.shop.admin.product.service.AdminProductImageService;
import pl.lukasik.shop.admin.product.service.AdminProductService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class AdminProductController {

    final private AdminProductService adminProductService;
    final private AdminProductImageService adminProductImageService;

    public AdminProductController(AdminProductService adminProductService,
                                  AdminProductImageService adminProductImageService) {
        this.adminProductService = adminProductService;
        this.adminProductImageService = adminProductImageService;
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
                .image(adminProductDto.getImage())
                .build()
        );
    }
    @PutMapping("/admin/product/{id}")
    public AdminProduct updateProduct(@RequestBody AdminProductDto adminProductDto, @PathVariable Long id){
        return adminProductService.updateProduct(AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .category(adminProductDto.getCategory())
                .description(adminProductDto.getDescription())
                .price(adminProductDto.getPrice())
                .currency(adminProductDto.getCurrency().toUpperCase())
                .image(adminProductDto.getImage())
                .build()
        );
    }

    @DeleteMapping("/admin/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        adminProductService.deleteProduct(id);
    }


    @PostMapping("/admin/product/image-upload")
    public UploadResponse uploadImage(@RequestParam("file") MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();

        try(InputStream inputStream = multipartFile.getInputStream()) {
            String savedFileName = adminProductImageService.uploadImage(filename, inputStream);
            return new UploadResponse(savedFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    @GetMapping("/data/productImg/{filename}")
    public ResponseEntity<Resource> serveFiles(@PathVariable String filename) throws IOException {

        Resource file = adminProductImageService.serveFiles(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(filename)))
                .body(file);

    }










}
