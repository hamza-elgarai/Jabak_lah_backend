package com.example.jl_entities.entity;

import com.example.jl_entities.userservice.Role;
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
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String fname;
    private String lname;
    private String email;
    @Column(unique = true,nullable = false)
    private String tel;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = AccountType.class)
    private AccountType type;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "client")
    List<Paiement> paiements;

    @OneToOne
    private CompteBancaire compteBancaire;

    private String verificationStatus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return tel;
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
