package pl.lukasik.shop.common.model;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
