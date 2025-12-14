package com.nguyenhongkien.fruitstore.repository;

import com.nguyenhongkien.fruitstore.entity.Order;
import com.nguyenhongkien.fruitstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;
@Repository

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    // Tổng doanh thu
    @Query("SELECT SUM(o.totalPrice) FROM Order o")
    Double getTotalRevenue();

    // ===== BIỂU ĐỒ DOANH THU THEO THÁNG =====
    @Query("""
        SELECT COALESCE(SUM(o.totalPrice), 0)
        FROM Order o
        WHERE FUNCTION('MONTH', o.orderDate) = :month
          AND o.status = com.nguyenhongkien.fruitstore.entity.OrderStatus.COMPLETED
    """)
    Double getRevenueByMonth(@Param("month") int month); // Thêm @Param cho tính an toàn
}