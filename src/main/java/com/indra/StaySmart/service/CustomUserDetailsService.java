package com.indra.StaySmart.service;

import com.indra.StaySmart.entity.AppUser;
import com.indra.StaySmart.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserInfoRepository userRepository;

    public CustomUserDetailsService(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Return a UserDetails object with the user's details and role
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword()) // Use the encoded password directly from DB
                .authorities("ROLE_" + appUser.getRole())  // Prefix role with ROLE_
                .build();
    }
}
