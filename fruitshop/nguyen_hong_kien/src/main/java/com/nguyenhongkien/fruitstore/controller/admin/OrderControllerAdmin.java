package com.nguyenhongkien.fruitstore.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nguyenhongkien.fruitstore.entity.Order;
import com.nguyenhongkien.fruitstore.service.OrderService;
import java.util.List;

@Controller
@RequestMapping("/admin/orders") // ÁNH XẠ CHÍNH XÁC: /admin/orders (số nhiều)
public class OrderControllerAdmin {

    @Autowired
    private OrderService orderService;

    // ================== DANH SÁCH ĐƠN HÀNG ==================
    // URL: GET /admin/orders
    @GetMapping("")
    public String list(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        // Trả về template admin/order/list.html
        return "admin/order/list";
    }

    // ================== CHI TIẾT ĐƠN HÀNG ==================
    // URL: GET /admin/orders/detail?id=X
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        Order order = orderService.findById(id).orElse(null);

        if (order == null) {
            // Chuyển hướng về danh sách: /admin/orders
            return "redirect:/admin/orders";
        }

        model.addAttribute("order", order);
        // Trả về template admin/order/order-details.html
        return "admin/order/order-details";
    }

    // Giả sử có thêm phương thức xử lý POST/LƯU đơn hàng
    // @PostMapping("/save")
    // public String saveOrder(@ModelAttribute Order order) {
    //     // ... logic save ...
    //     return "redirect:/admin/orders"; // Redirect về danh sách
    // }
}