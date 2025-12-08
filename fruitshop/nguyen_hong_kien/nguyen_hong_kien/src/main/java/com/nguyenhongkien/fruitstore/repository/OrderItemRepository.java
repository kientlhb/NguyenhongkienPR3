package com.nguyenhongkien.fruitstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nguyenhongkien.fruitstore.entity.Order;
import com.nguyenhongkien.fruitstore.entity.User;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    @Query("SELECT SUM(o.totalPrice) FROM Order o")
    Double getTotalRevenue();
}