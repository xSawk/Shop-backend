package pl.lukasik.shop.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

}
