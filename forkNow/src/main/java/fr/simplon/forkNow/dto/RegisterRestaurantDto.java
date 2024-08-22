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
public class RegisterRestaurantDto {
      @NotEmpty(message = "Name not found")
    private String name;
    @NotEmpty(message = "localisation not found")
    private String localisation;
    @NotEmpty(message = "spécialisation not found")
    private String specialisation;
    @NotEmpty(message = "price not found")
    private Integer priceOrder;
}