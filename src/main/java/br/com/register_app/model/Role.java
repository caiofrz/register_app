package br.com.register_app.model;

import br.com.register_app.enums.RoleName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "TB_ROLE")
@Schema(description = "User Roles Information")
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Role Id", example = "1")
  private int roleId;

  @Column(nullable = false, unique = true)
  @Enumerated(EnumType.STRING)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Role type", example = "USER")
  private RoleName roleName;

  @Override
  public String getAuthority() {
    return this.roleName.toString();
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public RoleName getRoleName() {
    return roleName;
  }

  public void setRoleName(RoleName roleName) {
    this.roleName = roleName;
  }
}
