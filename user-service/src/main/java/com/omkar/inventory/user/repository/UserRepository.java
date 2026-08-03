package com.omkar.inventory.user.repository;

import com.omkar.inventory.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    List<User> findByDepartment(String department);

    List<User> findByRole(String role);

    List<User> findByActive(Boolean active);
}