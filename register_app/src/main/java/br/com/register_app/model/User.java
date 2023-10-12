package br.com.register_app.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
@Schema(description = "User Model Information")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "User Id", example = "123")
    private int id;

    @Column(name="name", nullable=false)
    @NotBlank(message = "O NOME É OBRIGATÓRIO!")
    @Schema(description = "User name", example = "Caio Ferraz")
    private String name; 

    @Column(name="email", nullable=false, unique=true)
    @NotBlank(message = "O EMAIL É OBRIGATÓRIO!")
    @Email
    @Schema(description = "User valid email", example = "email@email.com")
    private String email;

    @Column(name="password", nullable=false)
    @NotBlank(message = "A SENHA É OBRIGATÓRIA!")
    @Size(min = 8, message = "A SENHA DEVE TER NO MÍNIMO 8 CARACTERES!")
    @Schema(description = "User password", example = "100senha")
    private String password;

    @Column(name="phone", nullable=false, length=14)
    @NotBlank(message = "O TELEFONE É OBRIGATÓRIO!")
    @Schema(description = "Tutorial's status (published or not)", example = "33999999999")
    private String phone;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
