package fr.simplon.forknow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.forknow.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByUsername(String username);
    Optional<User>findByEmail(String email);
}
