package pl.lukasik.shop.admin.order.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.lukasik.shop.admin.order.controller.dto.AdminInitDataDto;
import pl.lukasik.shop.admin.order.controller.dto.AdminOrderDto;
import pl.lukasik.shop.admin.order.controller.mapper.AdminOrderMapper;
import pl.lukasik.shop.admin.order.model.AdminOrder;
import pl.lukasik.shop.admin.order.model.AdminOrderStatus;
import pl.lukasik.shop.admin.order.service.AdminOrderService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService) {
        this.adminOrderService = adminOrderService;
    }

    @GetMapping
    public Page<AdminOrderDto> getOrders(Pageable pageable) {
        return AdminOrderMapper.mapToPageDtos(adminOrderService.getOrders(pageable));
    }

    @GetMapping("/{id}")
    public AdminOrder getOrders(@PathVariable Long id) {
        return adminOrderService.getOrder(id);
    }

    @PatchMapping("/{id}")
    public void patchOrder(@PathVariable Long id, @RequestBody Map<String,String> values) {
        adminOrderService.patchOrder(id,values);

    }

    @GetMapping("/initData")
    public AdminInitDataDto getInitData(){
        return new AdminInitDataDto(createOrderStatusMap());
    }

    private Map<String, String> createOrderStatusMap() {

        HashMap<String, String> statuses = new HashMap<>();
        for (AdminOrderStatus value : AdminOrderStatus.values()) {
            statuses.put(value.name(), value.name());
        }


        return statuses;
    }
}
