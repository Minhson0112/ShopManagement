package com.sounshop.ShopManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.sounshop.ShopManagement.repository.AdminRepository;
import com.sounshop.ShopManagement.entity.Admin;

public class AdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("user entered id: " + username);
        Admin admin = adminRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with id: " + username));
        System.out.println("found admin with id: " + admin.getUserId() + "and pass: " + admin.getPassword());
        return new AdminDetails(admin);
    }
}