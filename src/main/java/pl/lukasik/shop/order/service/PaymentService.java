package pl.lukasik.shop.order.service;


import org.springframework.stereotype.Service;
import pl.lukasik.shop.order.model.Payment;
import pl.lukasik.shop.order.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {


    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getPayments() {
        return paymentRepository.findAll();

    }
}
