package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.util.Collections;
import java.util.Map;

@Service
public class TokenValidator {

    private static final String GOOGLE_TOKEN_VALIDATION_URL = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=";

    public boolean validateToken(String token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = GOOGLE_TOKEN_VALIDATION_URL + token;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            // verifica que el token sea válido
            return response != null && response.get("audience") != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = GOOGLE_TOKEN_VALIDATION_URL + token;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.get("email") != null) {
                String email = (String) response.get("email");
                return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
