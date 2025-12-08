package com.nguyenhongkien.fruitstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nguyenhongkien.fruitstore.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);  // CODE GỐC

    // ⭐ BỔ SUNG — không làm thay đổi logic cũ
    Optional<User> findByEmail(String email);
}