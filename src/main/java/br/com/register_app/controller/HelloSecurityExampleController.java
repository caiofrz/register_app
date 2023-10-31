package br.com.register_app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloSecurityExampleController {

  @GetMapping(value = "/")
  public String hello() {
    return "Hello!";
  }

  @GetMapping(value = "/user")
  //Forma de autorização auternativa
//  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  public String helloUser() {
    return "Hello, User!";
  }

  @GetMapping(value = "/admin")
  //Forma de autorização auternativa
//  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String helloAdmin() {
    return "Hello, Admin!";
  }
}
