package com.nguyenhongkien.fruitstore.repository;

import com.nguyenhongkien.fruitstore.entity.Order;
import com.nguyenhongkien.fruitstore.entity.User; // Cần import entity User
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Cần import annotation Query
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Phương thức truy vấn theo ID đã có
    List<Order> findByUserId(Long userId);

    // KHẮC PHỤC LỖI: findByUser(User)
    // Dùng trường 'user' trong Entity Order
    List<Order> findByUser(User user);

    // KHẮC PHỤC LỖI: getTotalRevenue() VÀ LỖI 'totalAmount'
    // SỬA TÊN TRƯỜNG từ 'totalAmount' sang 'totalPrice'
    @Query("SELECT SUM(o.totalPrice) FROM Order o")
    Double getTotalRevenue();
}