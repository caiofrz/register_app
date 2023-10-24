package br.com.register_app.service;

import br.com.register_app.model.User;
import br.com.register_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository repository;

  public UserDetailServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            user.getAuthorities());
  }
}
