/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.Enum.UserRole;
import com.Feedy.model.User;
import com.Feedy.repository.UserRepo;
import com.Feedy.service.CustomerDetailService;
import org.apache.tomcat.util.net.jsse.JSSEImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("User not found with email "+email);
        }

        UserRole role = user.getRole();
//            role=UserRole.CUSTOMER;    //Defined in model UserRole.CUSTOMER default.

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role.toString()));

            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

    }
}
