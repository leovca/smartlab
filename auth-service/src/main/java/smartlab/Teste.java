package smartlab;

import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import java.util.Arrays;

/**
 * Created by leonan on 14/01/18.
 */
public class Teste {

    public static void main(String args[]){
        testClient();
    }

    public static void testClient() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername("usuario");
        resourceDetails.setPassword("123456");
        resourceDetails.setAccessTokenUri("http://localhost:5154/oauth/token");
        resourceDetails.setClientId("customer-service");
        resourceDetails.setClientSecret("secret");
        resourceDetails.setGrantType("password");
//        resourceDetails.setScope(Arrays.asList("read"));
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        //restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        final String customer = restTemplate.getForObject("http://localhost:5154/user", String.class, 1);
        System.out.println(restTemplate.getAccessToken());
    }
}
