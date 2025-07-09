package com.sa.imoveis.repository;

import com.sa.imoveis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    public User logIn(String email, String password);
}
