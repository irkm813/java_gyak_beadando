package com.example.java_gyak_beadando.login;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepo; // Dependency injection
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
// a user-t most email cím alapján azonosítjuk:
        User user = userRepo.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));

// Ez a User osztály implementálja a UserDetails interfészt
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                getAuthorities(user));
    }
    // A kiválasztott felhasználó szerepeinek lekérdezése:.
// A szerepek is olyan tulajdonsága a felhasználónak, mint a neve, címe, telefonszáma, …
    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return authorities;
    }
}