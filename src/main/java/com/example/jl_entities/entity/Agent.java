package com.example.jl_entities.entity;

import com.example.jl_entities.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agent implements UserDetails {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String fname;
    @Column(nullable = false)
    private String lname;
    @Column(nullable = false)
    private String identityType;
    @Column(nullable = false)
    private String identityNumber;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String immatriculation;
    @Column(nullable = false)
    private String licenseNumber;
    @Column(nullable = false)
    private String idFilePath;
    @ManyToOne
    private Agency agency;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
