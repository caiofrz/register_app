package br.com.register_app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="name", nullable=false)
    @NotBlank(message = "O NOME É OBRIGATÓRIO!")
    private String name; 

    @Column(name="email", nullable=false, unique=true)
    @NotBlank(message = "O EMAIL É OBRIGATÓRIO!")
    @Email
    private String email;

    @Column(name="password", nullable=false)
    @NotBlank(message = "A SENHA É OBRIGATÓRIA!")
    private String password;

    @Column(name="phone", nullable=false, length=14)
    @NotBlank(message = "O TELEFONE É OBRIGATÓRIO!")
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
