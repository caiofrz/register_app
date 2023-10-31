package br.com.register_app.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

  //Auth default Spring Security
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .httpBasic(Customizer.withDefaults())
//            .csrf().disable()
//            .authorizeHttpRequests()
//            .anyRequest().authenticated();
//
//    return http.build();
//  }

  //Configurando niveis de acesso a rotas
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .httpBasic(Customizer.withDefaults())
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/hello").permitAll()
            .requestMatchers(HttpMethod.GET, "/hello/user").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.GET, "/hello/admin").hasRole("ADMIN")
            .anyRequest().authenticated();

    return http.build();
  }

  //Definindo um ususario root default em memoria
//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails userDetails = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//    LOGGER.info(userDetails.toString());
//
//    return new InMemoryUserDetailsManager(userDetails);
//  }


  //Criando bean para o encoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}


