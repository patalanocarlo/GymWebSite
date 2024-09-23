package PassionIron.Project.GymWebSite.Controllers;

import PassionIron.Project.GymWebSite.Configurations.JWTTools;
import PassionIron.Project.GymWebSite.Entities.Abbonamento;
import PassionIron.Project.GymWebSite.Entities.Utente;
import PassionIron.Project.GymWebSite.Repository.AbbonamentoRepository;
import PassionIron.Project.GymWebSite.Repository.UtenteRepository;
import PassionIron.Project.GymWebSite.Service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private AbbonamentoRepository abbonamentoRepository;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;

    @GetMapping("/me")
    public String getCurrentUser(HttpServletRequest request) {

        String token = request.getHeader("Authorization");


        if (token == null || !token.startsWith("Bearer ")) {
            return "{\"error\": \"Accesso non autorizzato\"}";
        }


        token = token.substring(7);

        try {

            String email = jwtTools.extractEmailFromToken(token);


            Utente utente = utenteService.findByEmail(email);


            if (utente != null) {
                return "{ \"nome\": \"" + utente.getNome() + "\" }";
            } else {
                return "{\"error\": \"Utente non trovato\"}";
            }
        } catch (Exception e) {

            return "{\"error\": \"Token non valido\"}";
        }
    }
    @PutMapping("/{id}/abbonamento")
    public Utente updateAbbonamento(@PathVariable Long id, @RequestParam Long abbonamentoId) {
        Utente utente = utenteRepository.findById(id).orElse(null);
        if (utente != null) {

            Abbonamento abbonamento = abbonamentoRepository.findById(abbonamentoId).orElse(null);
            if (abbonamento != null) {
                utente.setAbbonamento(abbonamento);
                return utenteRepository.save(utente);
            }
        }
        return null;
    }
    }

