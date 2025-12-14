package com.nguyenhongkien.fruitstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nguyenhongkien.fruitstore.entity.Cart;
import com.nguyenhongkien.fruitstore.entity.User;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
