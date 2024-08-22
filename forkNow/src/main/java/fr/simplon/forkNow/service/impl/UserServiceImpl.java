package fr.simplon.forknow.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.simplon.forknow.dto.RegisterDto;
import fr.simplon.forknow.dto.UserDto;
import fr.simplon.forknow.model.User;
import fr.simplon.forknow.repository.UserRepository;
import fr.simplon.forknow.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public  UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; 
    }

    @Override
    public void saveUser(UserDto user) {
        User userEntity = User.builder()
            .username(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .displayName(user.getDisplayName())
            .build();
        userRepository.save(userEntity);
    }


    @Override
    public void saveUser(RegisterDto user) {
        User userEntity = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .build();
        userRepository.save(userEntity);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Optional<User> from(Authentication authentication) {
       if(authentication == null){
        return Optional.empty();
       }
       Object principal = authentication.getPrincipal();
       if(!(principal instanceof UserDetails)){
        return Optional.empty();
       }
       UserDetails userDetails = (UserDetails)principal;
       return userRepository.findByUsername(userDetails.getUsername());
    }


}
