package com.nguyenhongkien.fruitstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nguyenhongkien.fruitstore.entity.CartItem;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Dùng findById(id) sẵn có của JpaRepository
}