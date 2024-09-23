package PassionIron.Project.GymWebSite.Configurations;



import PassionIron.Project.GymWebSite.Entities.Utente;
import PassionIron.Project.GymWebSite.Service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String userId = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7).trim();
            userId = jwtTools.extractIdFromToken(jwt);
            System.out.println("JWT: " + jwt);
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Long id = Long.valueOf(userId);
            Utente utente = utenteService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + id));

            UserDetails userDetails = utente;

            try {
                jwtTools.verifyToken(jwt);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } catch (Exception e) {
                System.out.println("Invalid token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
