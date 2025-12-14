package com.nguyenhongkien.fruitstore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.nguyenhongkien.fruitstore.entity.Order;
import com.nguyenhongkien.fruitstore.entity.User;
import com.nguyenhongkien.fruitstore.service.OrderService;
import com.nguyenhongkien.fruitstore.service.UserService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // Trang danh sách đơn hàng
    @GetMapping("/user/orders")
    public String listUserOrders(Model model) {

        User currentUser = userService.getCurrentUser();   // ⭐ Lấy user hiện tại

        List<Order> orders = orderService.findByUser(currentUser);
        model.addAttribute("orders", orders);

        return "user/orders";
    }
}