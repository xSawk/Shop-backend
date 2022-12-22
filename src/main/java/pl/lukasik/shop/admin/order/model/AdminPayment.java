package pl.lukasik.shop.admin.order.model;

import lombok.Getter;
import pl.lukasik.shop.order.model.PaymentType;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Getter
public class AdminPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private boolean defaultPayment;
    private String note;
}

