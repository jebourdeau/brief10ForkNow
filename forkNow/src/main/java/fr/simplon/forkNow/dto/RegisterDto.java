package fr.simplon.forknow.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "Userame is empty!!")
    private String username;
    @NotEmpty(message = "Email is empty!!")
    private String email;
    @NotEmpty(message = "Password is empty!!")
    private String displayName;
    @NotEmpty(message = "not found")
    private String password;
    @NotEmpty(message = "PasswordConfirm is empty!!")
    private String passwordConfirm;
}
