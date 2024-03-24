package com.cmpt.focusdriving.models.Security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpt.focusdriving.models.User.UserRepository;

import java.util.List; // Make sure this is the import statement for List

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByName(username)
            .map(user -> {
                // Convert the user's role into a GrantedAuthority object
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
                return new org.springframework.security.core.userdetails.User(
                        user.getName(),
                        user.getPassword(),
                        authorities);
            })
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
}
}