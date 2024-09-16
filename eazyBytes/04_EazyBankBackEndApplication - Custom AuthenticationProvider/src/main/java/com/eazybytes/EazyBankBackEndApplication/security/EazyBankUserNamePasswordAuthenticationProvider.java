package com.eazybytes.EazyBankBackEndApplication.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eazybytes.EazyBankBackEndApplication.model.Customer;
import com.eazybytes.EazyBankBackEndApplication.repo.CustomerRepository;

@Component
public class EazyBankUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // return new UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities);
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Customer> customer = customerRepository.findByEmail(userName);
        if (customer.size() == 0) {
            throw new BadCredentialsException("No User registered with this email ! ");
        } else {
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
            if(passwordEncoder.matches(password, customer.get(0).getPwd()))
            {
                return new UsernamePasswordAuthenticationToken(userName, password, authorities);
            }
            else
            {
                throw new BadCredentialsException("Wrong password.....");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
}
