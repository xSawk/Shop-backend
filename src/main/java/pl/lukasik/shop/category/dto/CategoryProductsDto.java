package pl.lukasik.shop.category.dto;

import org.springframework.data.domain.Page;
import pl.lukasik.shop.common.dto.ProductListDto;
import pl.lukasik.shop.common.model.Category;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {

}
