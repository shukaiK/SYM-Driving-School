package com.cmpt.focusdriving.models;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.cmpt.focusdriving.models.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getName())
                        .password(user.getPassword())
                        .authorities("USER") // Or set appropriate roles/authorities based on your application's requirements
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}