// http://localhost:8080/login // http://localhost:8080/login/oauth2
package bo.edu.ucb.TECWEB_G1_Admin_backend.config;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.TokenValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityConfig(OAuth2AuthorizedClientService authorizedClientService) {}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, TokenValidator tokenValidator) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/login", "/oauth2/**", "/login/oauth2/**", "/api/v1/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login.successHandler(authenticationSuccessHandler()))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new TokenFilter(tokenValidator), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("http://localhost:8081");
        };
    }

}
