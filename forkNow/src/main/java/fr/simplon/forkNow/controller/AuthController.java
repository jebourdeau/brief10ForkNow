package fr.simplon.forknow.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.simplon.forknow.dto.RegisterDto;
import fr.simplon.forknow.dto.RegisterRestaurantDto;
import fr.simplon.forknow.model.User;
import fr.simplon.forknow.service.RestaurantService;
import fr.simplon.forknow.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthController {

  private final UserService userService;
  private final RestaurantService restaurantService;

  @Autowired
  public AuthController(UserService userService, RestaurantService restaurantService) {
    this.userService = userService;
    this.restaurantService = restaurantService;
  }

  @GetMapping("/")
  public String home(Model model, Authentication authentication) {
    Optional<User> user = userService.from(authentication);
    if (user.isPresent()) {
      model.addAttribute("currentUser", user.get());
    }
    return "forknow-index";
  }

  @GetMapping("/register")
  public String register(Model model) {
    RegisterDto user = new RegisterDto();
    model.addAttribute("user", user);
    return "forknow-register";
  }

  @GetMapping("/tasty")
    public String tasty(){
      return "forknow-tastybyte";
  }

  @GetMapping("/sushi-haven")
  public String sushi() {
    return "forknow-sushi";
  }

  @GetMapping("/pizza-paradise")
  public String pizza() {
    return "forknow-pizza";
  }

  @GetMapping("/restaurant")
  public String restaurant() {
    return "forknow-restaurants-list";
  }

  @GetMapping("/registerRestaurant")
  public String registRestaurant(Model model){
    RegisterRestaurantDto restaurant = new RegisterRestaurantDto();
    model.addAttribute("restaurant", restaurant);
    return "forknow-registerRestaurant";
  }

  @GetMapping("/login")
  public String login() {
    return "forknow-login";
  }
  
  @PostMapping("/register/save")
  public String registerUser(@Valid @ModelAttribute RegisterDto userMapping) {
    System.out.println("ca marche!!!!");
    if (!userMapping.getPassword().equals(userMapping.getPasswordConfirm())) {
      return "redirect:/register?error";
    }
    userService.saveUser(userMapping);
    return "redirect:/login?success=userRegistred";
  }

  @PostMapping("/registerRestaurant/save")
  public String registerRestaurant(@Valid @ModelAttribute RegisterRestaurantDto restaurantMapping) {
    System.out.println("ca creer!!!");
    restaurantService.saveRestaurant(restaurantMapping);
    return "redirect:/restaurant?success=userRegistred";
  }

}
