package bo.edu.ucb.TECWEB_G1_Admin_backend.config;

import bo.edu.ucb.TECWEB_G1_Admin_backend.bl.TokenValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {

    private final TokenValidator tokenValidator;

    public TokenFilter(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (tokenValidator.validateToken(token)) {
                SecurityContextHolder.getContext().setAuthentication(tokenValidator.getAuthentication(token));
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
