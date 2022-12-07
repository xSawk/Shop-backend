package pl.lukasik.shop.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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