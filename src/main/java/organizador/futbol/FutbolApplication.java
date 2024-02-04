package organizador.futbol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EntityScan(basePackages = "organizador.futbol.infrastructure.entities")
@EnableWebMvc
@SpringBootApplication()
public class FutbolApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutbolApplication.class, args);
	}

}
