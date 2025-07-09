package com.sa.imoveis.repository;

import com.sa.imoveis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmailAndPassword(String email, String password);
    public Optional<User> findByEmail(String email);
}
