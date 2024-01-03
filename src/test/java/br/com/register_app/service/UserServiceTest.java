package br.com.register_app.service;

import br.com.register_app.Exception.NotFoundException;
import br.com.register_app.enums.RoleName;
import br.com.register_app.model.Role;
import br.com.register_app.model.User;
import br.com.register_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Profile("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private BCryptPasswordEncoder passwordEncoder;

  @Mock
  private UserRepository repository;

  @InjectMocks
  private UserService service;

  private User user = new User();
  private Role roleUser = new Role();


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
  @DisplayName("Caso que testa a criação de um usuario com sucesso")
  void deveCriarUmUsuarioComSucesso() {

    when(repository.save(any(User.class))).thenReturn(user);

    var response = this.service.createUser(user);

    assertNotNull(response);
    assertEquals(user, response);
    assertEquals(User.class, response.getClass());

    assertEquals(user.getId(), response.getId());
    assertEquals(user.getUsername(), response.getUsername());
    assertEquals(user.getName(), response.getName());
    assertEquals(user.getPassword(), response.getPassword());
    assertEquals(user.getEmail(), response.getEmail());
    assertEquals(user.getPhone(), response.getPhone());
    assertEquals(user.getAuthorities(), response.getAuthorities());
  }

  @Test
  @DisplayName("Caso que testa o retorno de erro na criação de um usuario invalido")
  void deveRetornarErroAoCriarUmUsuarioInvalido() {
    assertThrows(IllegalArgumentException.class, () -> this.service.createUser(new User()));
  }


  @Test
  @DisplayName("Caso que testa o retorno com sucesso de um usuario")
  void deveRetornarUmUsuarioComSucesso() throws NotFoundException {

    when(repository.findById(any())).thenReturn(Optional.of(user));

    var response = this.service.getUser(1);

    assertNotNull(response);
    assertEquals(user, response);
    assertEquals(user.getUsername(), response.getUsername());
  }

  @Test
  @DisplayName("Caso que testa o retorno de erro no caso de não existir um usuario especifico")
  void deveRetornarUmErroNotFoundException() {

    assertThrows(NotFoundException.class, () -> this.service.getUser(1));
  }


  @Test
  @DisplayName("Caso que testa o retorno com sucesso da lista de usuarios")
  void deveRetornarTodosOsUsuariosComSucesso() {

    List<User> listUsers = List.of(user, new User(), new User());

    when(repository.findAll()).thenReturn(listUsers);

    var response = this.service.getUsers();

    assertNotNull(response);
    assertEquals(listUsers, response);
    assertEquals(listUsers.size(), response.size());
    assertEquals(User.class, response.get(0).getClass());
    assertEquals(user, response.get(0));
  }

  @Test
  @DisplayName("Caso que testa a atualização de um usuario com sucesso")
  void deveAtualizarUmUsuarioComSucesso() throws NotFoundException {

    when(repository.save(any(User.class))).thenReturn(user);
    when(repository.findById(user.getId())).thenReturn(Optional.of(user));

    var response = this.service.updateUser(user.getId(), user);

    assertNotNull(response);
    assertEquals(user, response);
    assertEquals(User.class, response.getClass());

    assertEquals(user.getId(), response.getId());
    assertEquals(user.getUsername(), response.getUsername());
    assertEquals(user.getName(), response.getName());
    assertEquals(user.getPassword(), response.getPassword());
    assertEquals(user.getEmail(), response.getEmail());
    assertEquals(user.getPhone(), response.getPhone());
    assertEquals(user.getAuthorities(), response.getAuthorities());
  }

  @Test
  @DisplayName("Caso que testa o retorno de erro na atualização de um usuario com dados invalidos")
  void deveRetornarErroAoAtualizarUmUsuarioComDadosInvalidos() {
    when(repository.findById(user.getId())).thenReturn(Optional.of(user));
    assertThrows(IllegalArgumentException.class, () -> this.service.updateUser(user.getId(), new User()));
  }

  @Test
  @DisplayName("Caso que testa o retorno de erro na atualização de um usuario não encontrado")
  void deveRetornarErroAoAtualizarUmUsuarioQueNaoFoiEncontrado() {
    assertThrows(NotFoundException.class, () -> this.service.updateUser(null, user));
  }

  @Test
  @DisplayName("Caso que testa a exclusão de um usuario com sucesso")
  void deveExcluirUmUsuarioComSucesso() throws NotFoundException {

    when(repository.findById(any())).thenReturn(Optional.of(user));
    doNothing().when(repository).deleteById(any());
    this.service.deleteUser(user.getId());

    verify(repository, times(1)).deleteById(any());
  }

  @Test
  @DisplayName("Caso que testa o retorno de erro na exclusão de um usuario não encontrado")
  void deveRetornarErroAoExcluirUmUsuarioQueNaoFoiEncontrado() {
    assertThrows(NotFoundException.class, () -> this.service.deleteUser(null));
  }

  @Test
  @DisplayName("Caso que testa a verificação de senha do usuario verdadeira")
  void deveRetornarTrueAoVerificarASenhaDoUsuario() {
    String email = "teste@teste.com";
    String password = "12345678";

    Map<String, String> login = new HashMap<>();
    login.put("email", email);
    login.put("password", password);

    when(repository.getByEmail(email)).thenReturn(user);
    when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

    boolean response = this.service.validatePassword(login);

    assertTrue(response);
  }

  @Test
  @DisplayName("Caso que testa a verificação de senha do usuario falsa")
  void deveRetornarFalseAoVerificarASenhaDoUsuario() {
    String email = "teste@teste.com";
    String password = "11111111111";

    Map<String, String> login = new HashMap<>();
    login.put("email", email);
    login.put("password", password);

    when(repository.getByEmail(email)).thenReturn(user);
    when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

    boolean response = this.service.validatePassword(login);

    assertFalse(response);
  }

  @Test
  @DisplayName("Caso que testa a verificação de senha de um usuario inexistente")
  void deveRetornarErroDeUsuarioNaoEncontradoAoVerificarASenhaDoUsuario() {
    String email = "teste@email.com";
    String password = "12345678";

    Map<String, String> login = new HashMap<>();
    login.put("email", email);
    login.put("password", password);

    when(repository.getByEmail(email)).thenThrow(new EmptyResultDataAccessException(anyInt()));

    try {
      boolean response = this.service.validatePassword(login);
    } catch (Exception ex) {
      assertEquals(EmptyResultDataAccessException.class, ex.getClass());
    }

  }


}
