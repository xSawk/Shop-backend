package pl.lukasik.shop.admin.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lukasik.shop.admin.order.model.AdminOrder;
import pl.lukasik.shop.admin.order.model.AdminOrderStatus;
import pl.lukasik.shop.admin.order.repository.AdminOrderRepository;

import java.util.Map;

@Service
public class AdminOrderService {

    private final AdminOrderRepository orderRepository;

    public AdminOrderService(AdminOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<AdminOrder> getOrders(Pageable pageable) {
        return orderRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by("id").descending())
        );
    }

    public AdminOrder getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void patchOrder(Long id, Map<String, String> values) {
        AdminOrder order = orderRepository.findById(id).orElseThrow();
        patchValues(order, values);
    }

    private void patchValues(AdminOrder order, Map<String, String> values) {
        System.out.println(values.get("orderStatus"));
        if(values.get("orderStatus") != null){
            order.setOrderStatus(AdminOrderStatus.valueOf(values.get("orderStatus")));
        }
    }
}
