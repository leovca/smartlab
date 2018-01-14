package smartlab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;
import smartlab.model.User;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceApplicationTests {

	@Test
	public void testClient() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setUsername("piomin");
		resourceDetails.setPassword("piot123");
		resourceDetails.setAccessTokenUri("http://localhost:5154/oauth/token");
		resourceDetails.setClientId("customer-service");
		resourceDetails.setClientSecret("secret");
		resourceDetails.setGrantType("password");
		resourceDetails.setScope(Arrays.asList("read"));
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
		//restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
		final String customer = restTemplate.getForObject("http://localhost:5154/user", String.class, 1);
		System.out.println(customer);
	}

	@Test
	public void contextLoads() {
	}

}
