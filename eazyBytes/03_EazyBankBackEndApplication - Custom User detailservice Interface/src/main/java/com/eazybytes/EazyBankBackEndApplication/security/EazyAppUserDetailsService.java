package com.eazybytes.EazyBankBackEndApplication.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eazybytes.EazyBankBackEndApplication.model.Customer;
import com.eazybytes.EazyBankBackEndApplication.repo.CustomerRepository;

@Service
public class EazyAppUserDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByEmail(username);
        String userName;
        String passWord;
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(customers.size()==0)
        {
            throw new UsernameNotFoundException("Given email ID is not present in DB");
        }
        else
        {
            //User(String username, String password, Collection<? extends GrantedAuthority> authorities)
            userName = customers.get(0).getEmail();
            passWord = customers.get(0).getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }
        return new User(userName, passWord, authorities);
    }
}
