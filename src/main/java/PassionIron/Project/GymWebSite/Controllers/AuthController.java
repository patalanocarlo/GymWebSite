package PassionIron.Project.GymWebSite.Controllers;

import PassionIron.Project.GymWebSite.Configurations.JWTTools;
import PassionIron.Project.GymWebSite.Entities.Utente;

import PassionIron.Project.GymWebSite.Payloadds.LoginRequestPayload;
import PassionIron.Project.GymWebSite.Payloadds.RegisterRequestPayload;
import PassionIron.Project.GymWebSite.Service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestPayload registerRequest) {
        // Verifica se l'email è già registrata
        if (utenteService.findByEmail(registerRequest.getEmail()) != null) {
            return "Email già in uso.";
        }

        Utente utente = new Utente();
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setEmail(registerRequest.getEmail());
        utente.setPassword(passwordEncoder.encode(registerRequest.getPassword()));


        utenteService.createUtente(utente);

        return "Registrazione completata.";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestPayload loginRequest) {
        Utente utente = utenteService.findByEmail(loginRequest.getEmail());
        if (utente != null && passwordEncoder.matches(loginRequest.getPassword(), utente.getPassword())) {
            // Genera il token JWT
            String token = jwtTools.createToken(utente);
            return "Bearer " + token;
        } else {
            return "Credenziali non valide.";
        }
    }
}
