package bo.edu.ucb.TECWEB_G1_Admin_backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthApi {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public AuthApi(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    /** Endpoint que retorna la información del usuario autenticado.
     */
    @GetMapping("/user-info")
    public Map<String, Object> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof OAuth2User oauthUser)) {
            throw new RuntimeException("El usuario no está autenticado");
        }

        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                "google",
                authentication.getName()
        );

        if (authorizedClient == null) {
            throw new RuntimeException("Autorización no encontrada para el usuario: " + authentication.getName());
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("access_token", authorizedClient.getAccessToken().getTokenValue());
        responseBody.put("name", oauthUser.getAttribute("name"));
        responseBody.put("email", oauthUser.getAttribute("email"));

        return responseBody;
    }
}
