package kz.ne.railways.asudkr.os.changeit.bean.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
public class KeycloakConfigBean {

    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
    @Value("${keycloak.auth-server-url}")
    private String url;


    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder().clientId(clientId).clientSecret(clientSecret).realm(realm)
                        .grantType(CLIENT_CREDENTIALS).serverUrl(url).build();
    }

    @Bean
    public AuthzClient authzClient() {
        Map<String, Object> clientSecretMap = new HashMap<>();
        clientSecretMap.put("secret", clientSecret);

        org.keycloak.authorization.client.Configuration configuration =
                        new org.keycloak.authorization.client.Configuration(url, realm, clientId, clientSecretMap,
                                        null);

        return AuthzClient.create(configuration);
    }
}
