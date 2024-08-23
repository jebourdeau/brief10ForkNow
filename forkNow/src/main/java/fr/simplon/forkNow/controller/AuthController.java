package fr.simplon.forknow.controller;

import java.util.List;
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
import fr.simplon.forknow.model.Restaurant;
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

  @GetMapping("/login")
  public String login() {
    return "forknow-login";
  }

  @GetMapping("/profil")
  public String profil(Model model, Authentication authentication) {
    Optional<User> user = userService.from(authentication);
    if (user.isPresent()) {
      model.addAttribute("user", user.get());
    }
    return "forknow-profil";
  }

  @GetMapping("/register")
  public String register(Model model) {
    RegisterDto user = new RegisterDto();
    model.addAttribute("user", user);
    return "forknow-register";
  }

  @GetMapping("/restaurant")
  public String restaurant(Model model){
    List<Restaurant> restaurant = restaurantService.findAll();
    model.addAttribute("restaurants", restaurant);
    return "forknow-restaurants-list";
  }

  @GetMapping("/tasty")
  public String tasty() {
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

  @GetMapping("/registerRestaurant")
  public String registRestaurant(Model model){
    RegisterRestaurantDto restaurant = new RegisterRestaurantDto();
    model.addAttribute("restaurant", restaurant);
    return "forknow-registerRestaurant";
  }
  
  @PostMapping("/register/save")
  public String registerUser(@Valid @ModelAttribute RegisterDto userMapping) {
    System.out.println("user creer en bdd!!!!");
    if (!userMapping.getPassword().equals(userMapping.getPasswordConfirm())) {
      return "redirect:/register?error";
    }
    userService.saveUser(userMapping);
    return "redirect:/profil?success=userRegistred";
  }

  @PostMapping("/registerRestaurant/save")
  public String registerRestaurant(@Valid @ModelAttribute RegisterRestaurantDto restaurantMapping) {
    System.out.println("restaurant creer en bdd!!!");
    restaurantService.saveRestaurant(restaurantMapping);
    return "redirect:/restaurant?success=restaurantRegistred";
  }

}
