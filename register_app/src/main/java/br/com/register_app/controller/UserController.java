package br.com.register_app.controller;

import br.com.register_app.Exception.NotFoundException;
import br.com.register_app.model.User;
import br.com.register_app.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*") //Liberar entradas da mesma máquina
@Tag(name = "User")
public class UserController {

  @Autowired
  private UserService service;

  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
          @ApiResponse(responseCode = "404", description = "Users not found.", content = {@Content(schema = @Schema())})
  })
  @GetMapping(value = "/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.status(HttpStatus.OK)
            .body(this.service.getUsers());
  }

  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
  })
  @GetMapping(value = "/user/{id}")
  public ResponseEntity<User> getUser(@PathVariable Integer id) throws NotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
            .body(this.service.getUser(id));
  }


  @Parameters({
          @Parameter(name = "email", description = "user email", required = true),
          @Parameter(name = "password", description = "user password", required = true),
          @Parameter(name = "phone", description = "user phone", required = true),
  })
  @PostMapping(value = "/user")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(this.service.createUser(user));
  }

  @Parameters({
          @Parameter(name = "id", description = "user id", required = false),
          @Parameter(name = "email", description = "user email", required = true),
          @Parameter(name = "password", description = "user password", required = true),
          @Parameter(name = "phone", description = "user phone", required = true),
  })
  @PutMapping(value = "/user/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) throws NotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(this.service.updateUser(id, user));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "204"),
  })
  @DeleteMapping(value = "/user/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable Integer id) throws NotFoundException {
    this.service.deleteUser(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Parameters({
          @Parameter(name = "email", description = "user email", required = true),
          @Parameter(name = "password", description = "user password", required = true),
  })
  @PostMapping(value = "/user/login")
  public ResponseEntity<User> validatePassword(@RequestBody Map<String, String> login) {

    if (!this.service.validatePassword(login)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
