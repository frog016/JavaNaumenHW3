package org.example.JavaNaumenHW3.Services;

import org.example.JavaNaumenHW3.Entity.User;
import org.example.JavaNaumenHW3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MyUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public MyUserDetailService(UserService userService, UserRepository userRepository) {
        this.userService = userService;

        userService.createUser("person1", "mail", "8999", "123", "admin");
        userService.createUser("person2", "mail2", "8111", "123", "user");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByFullName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getFullName(),
                    user.getPassword(),
                    mapRoles(user));
        }
        else {
            throw new UsernameNotFoundException("user not found");
        }

    }

    private Collection<GrantedAuthority> mapRoles(User user) {
        var roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return roles;
    }
}
