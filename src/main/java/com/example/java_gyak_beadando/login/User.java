package com.example.java_gyak_beadando.Login;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username; // Ez az egyedi felhasználónév

    @Column(nullable = false, unique = true)
    private String email; // Egyedi e-mail cím, ha valaha kell

    @Column(nullable = false)
    private String password; // Jelszó

    @Transient
    private String confirmpassword; // Jelszó megerősítés, nem kerül adatbázisba

    private String role; // Felhasználói szerep

    // Getterek és setterek
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmpassword;
    }

    public void setConfirmPassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
