package com.eazybytes.EazyBankBackEndApplication.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eazybytes.EazyBankBackEndApplication.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTokenGenerationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Inside JWTokenGenerationFilter..........************************&&&&&&&&&&&&");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null!=authentication)
        {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("EazyApp").setSubject("JWT TOKEN")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(key).compact();

            System.out.println("Soooooooooooooooooooooooooooooooooooooooo the created JWT Token is - "+jwt);
            
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }
        filterChain.doFilter(request, response);
    }

    private Object populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority:authorities)
        {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user");
    }
        
    
}
