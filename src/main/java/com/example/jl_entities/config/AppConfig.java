package com.example.jl_entities.config;


import com.example.jl_entities.entity.Client;
import com.example.jl_entities.repository.AgentRepository;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.userservice.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username ->{
            return userRepository.findByEmail(username).orElseThrow();
        } ;
    }
    @Bean
    public UserDetailsService agentDetailsService(){
        return username ->{
            UserDetails user = agentRepository.findByUsername(username).orElse(null);
            return user;
        } ;
    }

    @Bean
    public UserDetailsService clientDetailsService(){
        return username -> {
            System.out.println("clientDetailsService");
            System.out.println(username);
            UserDetails user = clientRepository.findByTel(username).orElse(null);
            System.out.println(user);
            if(user == null) throw new UsernameNotFoundException("User not found");
            return user;
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationProvider agentAuthProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(agentDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationProvider clientAuthProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(clientDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
