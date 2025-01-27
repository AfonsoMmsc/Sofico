package com.sofico_backend.sofico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sofico_backend.sofico.models.UserClient;
import com.sofico_backend.sofico.repository.UserRepository;

public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public Optional<UserClient> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public List<UserClient> findAll() {
        return userRepository.findAll();
    }

    public UserClient saveUser(UserClient user) {
        return userRepository.save(user);
    }

    public UserClient updateUser(UserClient user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isAdmin(String username) {
        UserClient user = userRepository.findByUsername(username);
        return user != null && user.isAdmin();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserClient user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return user;
    }
}
