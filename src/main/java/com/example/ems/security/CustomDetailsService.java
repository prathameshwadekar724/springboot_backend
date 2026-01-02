package com.example.ems.security;

import com.example.ems.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;
@Service
public class CustomDetailsService implements UserDetailsService{
    private final UserRepository repository;

    public CustomDetailsService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().replace("ROLE_",""))
                .build();
    }
}
