package com.nguyenhongkien.fruitstore.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nguyenhongkien.fruitstore.repository.OrderRepository;
import com.nguyenhongkien.fruitstore.repository.UserRepository;
import com.nguyenhongkien.fruitstore.repository.ReviewRepository;
;
@Controller
@RequestMapping("/admin")

public class ControllerAdmin {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // Trang dashboard chính
    @GetMapping({"", "/", "/index"})
    public String adminHome(Model model) {
        // 1. Doanh thu thực tế
        Double totalRevenue = orderRepository.getTotalRevenue();
        if (totalRevenue == null) totalRevenue = 0.0;

        // 2. Số lượng user
        long userCount = userRepository.count();

        // 3. Lượt mua (tổng số đơn hàng)
        long purchaseCount = orderRepository.count();

        // 4. Đánh giá trung bình
        Double averageRating = reviewRepository.getAverageRating();
        if (averageRating == null) averageRating = 0.0;

        // Thêm dữ liệu vào model
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("userCount", userCount);
        model.addAttribute("purchaseCount", purchaseCount);
        model.addAttribute("averageRating", String.format("%.1f/5", averageRating));

        return "admin/index";  // Trả về template admin/index.html
    }
}
