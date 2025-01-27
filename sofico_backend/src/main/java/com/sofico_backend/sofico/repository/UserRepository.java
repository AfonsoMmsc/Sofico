package com.sofico_backend.sofico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofico_backend.sofico.models.UserClient;

public interface UserRepository extends JpaRepository<UserClient, Long> {
    UserClient findByUsername(String username);
}