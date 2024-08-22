package fr.simplon.forknow.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import fr.simplon.forknow.dto.RegisterDto;
import fr.simplon.forknow.dto.UserDto;
import fr.simplon.forknow.model.User;


public interface UserService {
    void saveUser(UserDto user);
    void saveUser(RegisterDto userMapping);
    
    Optional<User>findByUsername(String username);
    Optional<User>findByEmail(String email);
    Optional<User>from(Authentication authentication);

}
