package pl.lukasik.shop.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lukasik.shop.admin.controller.dto.AdminProductDto;
import pl.lukasik.shop.admin.model.AdminProduct;
import pl.lukasik.shop.admin.repo.AdminProductRepository;

@Service
public class AdminProductService {

    final private AdminProductRepository adminProductRepository;

    public AdminProductService(AdminProductRepository adminProductRepository) {
        this.adminProductRepository = adminProductRepository;
    }

    public Page<AdminProduct> getProducts(Pageable pageable){
        return adminProductRepository.findAll(pageable);
    }

    public AdminProduct getProduct(Long id) {
        return adminProductRepository.findById(id).orElseThrow();
    }

    public AdminProduct addProduct(AdminProduct adminProduct){
        return adminProductRepository.save(adminProduct);
    }
    public AdminProduct updateProduct(AdminProduct adminProduct){
        return adminProductRepository.save(adminProduct);
    }

    public void deleteProduct(Long id) {
        adminProductRepository.deleteById(id);
    }
}
