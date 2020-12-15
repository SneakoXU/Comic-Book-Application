package com.techelevator.controller;

import java.security.Principal;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.LoginDTO;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDAO userDAO;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDAO userDAO) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDto) {
    	System.out.println(loginDto.toString());
    	String hash ="";
    	for(int i = 0; i < loginDto.getPassword().length()-2; i++)
    		hash += "*";
    	System.out.println("Logging in user " + loginDto.getUsername() + " with password " + loginDto.getPassword().substring(0,2) + hash);
    	userDAO.setIsOnline(userDAO.findIdByUsername(loginDto.getUsername()), true);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDAO.findByUsername(loginDto.getUsername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);	
    }
    
    @RequestMapping(value = "/loginraw", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> loginraw(@Valid  LoginDTO loginDto) {
    	
    	String hash ="";
    	for(int i = 0; i < loginDto.getPassword().length()-2; i++)
    		hash += "*";
    	System.out.println("Logging in user: " + loginDto.getUsername() + " with password: " + loginDto.getPassword().substring(0,2) + hash);
    	
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDAO.findByUsername(loginDto.getUsername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody RegisterUserDTO newUser) { 
    	System.out.println("Registering user " + newUser.getUsername());
    	String hash ="";
    	for(int i = 0; i < newUser.getPassword().length()-2; i++)
    		hash += "*";
    	
        try {
            User user = userDAO.findByUsername(newUser.getUsername());
            throw new ResponseStatusException(
        	          HttpStatus.CONFLICT, "User already exists");
        } 
        catch (UsernameNotFoundException e) 
        {
        	System.out.println("Creating user");
            userDAO.create(newUser.getUsername(),newUser.getPassword(), newUser.getRole()); 
            System.out.println("Logging in user " + newUser.getUsername() + " with password " + newUser.getPassword().substring(0,2) + hash);
        	userDAO.setIsOnline(userDAO.findIdByUsername(newUser.getUsername()), true);
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        
    }
    
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(Principal principal) { 
    	System.out.println("Logging out user " + principal.getName());
    	userDAO.setIsOnline(userDAO.findIdByUsername(principal.getName()), false);
    }

    /**
     * Object to return as body in JWT Authentication. 
     */
    static class LoginResponse {

        private String token;
        private User user;

        LoginResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }

        @JsonProperty("token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }

        @JsonProperty("user")
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
    }
}

