/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.controller;


import com.Feedy.Enum.UserRole;
import com.Feedy.configuration.JwtProvider;
import com.Feedy.model.Cart;
import com.Feedy.model.User;
import com.Feedy.repository.CartRepo;
import com.Feedy.repository.UserRepo;
import com.Feedy.request.LoginRequest;
import com.Feedy.response.AuthResponse;
import com.Feedy.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerDetailService customerDetailService;

    @Autowired
    private CartRepo cartRepo;


    // Login user method start from here
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loggingIn(@RequestBody LoginRequest request){
        String email = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticate(email,password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
        authResponse.setUserRole(UserRole.valueOf(role));

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    public Authentication authenticate(String email, String password){

        UserDetails userDetails = customerDetailService.loadUserByUsername(email);

        if (userDetails==null){
            throw new BadCredentialsException("Invalid Username.....");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username.....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }



    // Login user method start from here




    // Register new user method start from here

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist = userRepo.findByEmail(user.getEmail());

        if(isEmailExist!=null){
            throw  new Exception("Email is already used in another account");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(createdUser);


        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepo.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setUserRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    // Register new user method ends here

}
