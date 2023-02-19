package KameleoonTrialTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebConfig {

    private final UserDetailsService userDetailsService;

    public WebConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and().csrf().disable().headers().frameOptions().disable()
                .and().authorizeHttpRequests(auth -> {
                    auth.mvcMatchers(HttpMethod.POST, "/quote").authenticated();
                    auth.mvcMatchers(HttpMethod.DELETE, "/quote/*").authenticated();
                    auth.mvcMatchers( "/vote/**", "/quote", "/vote").authenticated();
                    auth.mvcMatchers("/**").permitAll();
                    //auth.mvcMatchers("/**").authenticated();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }

}
