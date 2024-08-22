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

public class UserDto {
    @NotEmpty(message = "Enter a Username")
    private String username;
    @NotEmpty(message = "Password not found")
    private String password;
    @NotEmpty(message = "Email is empty!!")
    private String email;
    @NotEmpty(message = "DisplayName is empty!!")
    private String displayName;
    
}
