package com.eazybytes.EazyBankBackEndApplication.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eazybytes.EazyBankBackEndApplication.model.Authority;
import com.eazybytes.EazyBankBackEndApplication.model.Customer;
import com.eazybytes.EazyBankBackEndApplication.repo.CustomerRepository;

@Component
public class EazyBankppAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        // List<GrantedAuthority> authorities = new ArrayList<>();    - Commenting this line since we have created method getGrantedAuthorities() for fetching authority details
        List<Customer> customer = customerRepository.findByEmail(userName);
        if (customer.size() == 0) {
            throw new BadCredentialsException("No User registered with this email ! ");
        } else {
            // authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));    - Commenting this line since we have created method getGrantedAuthorities() for fetching authority details
            if (passwordEncoder.matches(password, customer.get(0).getPwd())) {
                return new UsernamePasswordAuthenticationToken(userName, password, getGrantedAuthorities(customer.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Wrong password.....");
            }
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities)
    {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
}
