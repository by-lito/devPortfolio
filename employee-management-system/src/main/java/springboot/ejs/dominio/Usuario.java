package springboot.ejs.dominio;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "app_users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    @Column(nullable = false, length = 60)
    private String password; // Aquí guardamos el HASH

    @Setter
    @Column(nullable = false)
    private String role; // ADMIN o USER

    public Usuario() {
    }

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}

