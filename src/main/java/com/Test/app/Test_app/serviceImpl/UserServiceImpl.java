package com.Test.app.Test_app.serviceImpl;

import com.Test.app.Test_app.io.entities.users;
import com.Test.app.Test_app.io.repository.UserRepository;
import com.Test.app.Test_app.service.UserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserServiceImpl implements UserSerive {
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users user = userRepository.findByName(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        else
            return User.builder().username(user.getName()).password(user.getPassword()).roles(getRoles(user)).build();
//            return new org.springframework.security.core.userdetails.User(
//                    user.getName(),
//                    user.getPassword(),
//                    Set.of(new SimpleGrantedAuthority("ROLE_" + user.getRoleId().getName()))
//            );
    }

    private String[] getRoles(users user) {
        if (user.getRoleId().getName() == null) {
            return new String[]{"USER"};
        }
        return user.getRoleId().getName().split(",");
    }

    private Set<SimpleGrantedAuthority> getAuthorities(users user) {
        if (user.getRoleId() != null && user.getRoleId().getName() != null) {
            return Set.of(new SimpleGrantedAuthority(user.getRoleId().getName()));
        }
        return Collections.emptySet();
    }
}
