package smartlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;

//@SpringBootApplication
//@EnableAuthorizationServer
//@EnableDiscoveryClient
//@EnableResourceServer
//@RestController
//@EnableJdbcHttpSession
@EnableSpringHttpSession
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@SessionAttributes("authorizationRequest")
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository();
	}
	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
