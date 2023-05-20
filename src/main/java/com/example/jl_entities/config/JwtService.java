package com.example.jl_entities.config;

import com.example.jl_entities.repository.AgentRepository;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.userservice.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;

    private static final String SECRET_KEY = "26452948404D635166546A576E5A7234753778217A25432A462D4A614E645267";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> roles = new HashMap<>();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<String> rolesList = new ArrayList<>();
        for(GrantedAuthority authority:authorities){
            rolesList.add(authority.getAuthority());
        }
        roles.put("roles",rolesList);
        return generateToken(roles, userDetails);
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails){

        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails)
    {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String generateFromRefreshToken(String refreshToken,String role){
        String username = extractUsername(refreshToken);
        UserDetails user;
        if(role.equals("AGENT")){
            user = agentRepository.findByUsername(username).orElse(null);
        } else if (role.equals("CLIENT")) {
            user = clientRepository.findByTel(username).orElse(null);
        } else{
            user = userRepository.findByEmail(username).orElse(null);
        }
        Map<String,Object> roles = new HashMap<>();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        List<String> rolesList = new ArrayList<>();
        for(GrantedAuthority authority:authorities){
            rolesList.add(authority.getAuthority());
        }
        roles.put("roles",rolesList);
        if(user==null) return null;
        return generateToken(roles,user);
    }




    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public List<String> extractRoles(String token){
        return extractAllClaims(token).get("roles",List.class);
    }



    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
