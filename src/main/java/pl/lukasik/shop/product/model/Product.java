package pl.lukasik.shop.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.lukasik.shop.category.model.Category;
import pl.lukasik.shop.review.model.Review;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private Long categoryId;
   private String description;
   private BigDecimal price;
   private String currency;
   private String image;
   @OneToMany
   @JoinColumn(name = "productId")
   private List<Review> reviews;
}
