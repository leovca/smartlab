package smartlab;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

//	@Bean
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().url("jdbc:mysql://192.168.99.100:33306/oauth2?useSSL=false")
//				.username("default").password("default").driverClassName("com.mysql.jdbc.Driver").build();
//	}
}
