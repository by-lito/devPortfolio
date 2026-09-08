package springboot.ejs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springboot.ejs.dominio.Usuario;
import springboot.ejs.repositorios.UsuarioRepository;


@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (usuarioRepository.findByUsername("admin").isEmpty()) {

            String passwordCifrada = passwordEncoder.encode("admin");

            Usuario admin = new Usuario("admin", passwordCifrada, "ADMIN");

            usuarioRepository.save(admin);

            System.out.println("--------------------------------------------------");
            System.out.println("Usuario admin creado correctamente");
            System.out.println("Hash generado: " + passwordCifrada);
            System.out.println("--------------------------------------------------");
        }
    }
}

