package br.com.register_app.controller;

import br.com.register_app.Exception.NotFoundException;
import br.com.register_app.enums.RoleName;
import br.com.register_app.model.Role;
import br.com.register_app.model.User;
import br.com.register_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Profile("test")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserService service;

  @InjectMocks
  private UserController controller;

  private final User user = new User();
  private final Role roleUser = new Role();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.start();
  }

  private void start() {
    user.setId(1);
    user.setName("Caio");
    user.setEmail("teste@teste.com");
    user.setPassword("12345678");
    user.setPhone("33988237292");

    roleUser.setRoleId(1111);
    roleUser.setRoleName(RoleName.ROLE_USER);
    user.setRoles(List.of(roleUser));
  }

  @Test
  @DisplayName("Caso que testa a resposta de sucesso da API na recuperação de um usuario especifico")
  void deveRetornarARespostaOkEOUsuarioComSucesso() throws NotFoundException {

    when(service.getUser(anyInt())).thenReturn(user);

    var response = controller.getUser(1);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(User.class, response.getBody().getClass());
    assertEquals(user, response.getBody());
    assertEquals(1, response.getBody().getId());
  }

  @Test
  @DisplayName("Caso que testa a resposta de usuario não encontrado da API na recuperação de um usuario especifico")
  void deveRetornarARespostaNotFound() throws NotFoundException {
    when(service.getUser(anyInt())).thenThrow(new NotFoundException());
    assertThrows(NotFoundException.class, () -> controller.getUser(1));
  }

  @Test
  @DisplayName("Caso que testa a resposta de sucesso da API na recuperação de todos os usuarios")
  void deveRetornarARespostaOkEAListaDeTodosOsUsuarios() {
    List<User> listUsers = List.of(user, new User(), new User());

    when(service.getUsers()).thenReturn(listUsers);

    var response = controller.getUsers();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(listUsers, response.getBody());
    assertEquals(listUsers.size(), response.getBody().size());
    assertEquals(User.class, response.getBody().get(0).getClass());
    assertEquals(user, response.getBody().get(0));
    assertEquals(1, response.getBody().get(0).getId());
  }

  @Test
  @DisplayName("Caso que testa a resposta de sucesso da API na recuperação de todos os usuarios, quando não há usuarios")
  void deveRetornarARespostaOkEAListaVazia() {
    List<User> listUsers = List.of();

    when(service.getUsers()).thenReturn(listUsers);

    var response = controller.getUsers();

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(listUsers, response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  @DisplayName("Caso que testa a resposta de criado da API no cadastro de usuario")
  void deveRetornarARespostaCreatedEOUsuario() {

    when(service.createUser(any(User.class))).thenReturn(user);

    var response = controller.createUser(user);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(User.class, response.getBody().getClass());
    assertEquals(user, response.getBody());
    assertEquals(1, response.getBody().getId());
  }

  @Test
  @DisplayName("Caso que testa a resposta de erro da API no cadastro de usuario invalido")
  void deveRetornarErroNaCriacaoDeUsuarioInvalido() {

    when(service.createUser(any(User.class))).thenThrow(new IllegalArgumentException());

    assertThrows(IllegalArgumentException.class, () -> this.service.createUser(new User()));
  }

  @Test
  @DisplayName("Caso que testa a resposta de criado da API na atualização de um usuario")
  void deveRetornarARespostaCreatedEOUsuarioAtualizado() throws NotFoundException {

    when(service.updateUser(anyInt(), any(User.class))).thenReturn(user);
    user.setName("CaioFerraz");

    var response = controller.updateUser(1, user);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(User.class, response.getBody().getClass());
    assertEquals(user, response.getBody());
    assertEquals(1, response.getBody().getId());
  }

  @Test
  @DisplayName("Caso que testa a resposta de usuario não encontrado da API na atualização de um usuario inexistente")
  void deveRetornarARespostaNotFoundNaAtualizacaoDeUmUsuario() throws NotFoundException {
    when(service.updateUser(anyInt(), any(User.class))).thenThrow(new NotFoundException());
    assertThrows(NotFoundException.class, () -> controller.updateUser(1, user));
  }

  @Test
  @DisplayName("Caso que testa a exclusão de um usuario com sucesso")
  void deveExcluirUmUsuarioComSucesso() throws NotFoundException {

    doNothing().when(service).deleteUser(anyInt());
    this.controller.deleteUser(user.getId());

    verify(service, times(1)).deleteUser(anyInt());
  }



  @Test
  void getUser() {
  }

  @Test
  void createUser() {
  }

  @Test
  void updateUser() {
  }

  @Test
  void deleteUser() {
  }

  @Test
  void validatePassword() {
  }
}