package com.pie.planingapispring.repository;

import com.pie.planingapispring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findAllByPseudoContainingOrEmailContaining(String pseudo, String email);
}
