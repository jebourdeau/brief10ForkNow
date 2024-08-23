package fr.simplon.forknow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

        private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
       @Autowired
    public SpringSecurity(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers( "/register").permitAll()
                            .requestMatchers("/tasty").permitAll()
                            .requestMatchers("/sushi-haven").permitAll()
                            .requestMatchers("/pizza-paradise").permitAll()
                            .requestMatchers("/forlogin").permitAll()
                            .requestMatchers("/register/save").permitAll()
                            .requestMatchers("/restaurant").permitAll()
                            .requestMatchers("/registerRestaurant").permitAll()
                            .requestMatchers("/registerRestaurant/save").authenticated()
                            .requestMatchers("/css/**", "/favicon.ico").permitAll()
                            .requestMatchers("/img/**").permitAll();
                }).formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll())
                .build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
