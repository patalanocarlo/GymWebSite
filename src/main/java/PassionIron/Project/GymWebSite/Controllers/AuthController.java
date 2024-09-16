package PassionIron.Project.GymWebSite.Controllers;

import PassionIron.Project.GymWebSite.Configurations.JWTTools;
import PassionIron.Project.GymWebSite.Entities.Utente;

import PassionIron.Project.GymWebSite.Payloadds.LoginRequestPayload;
import PassionIron.Project.GymWebSite.Payloadds.RegisterRequestPayload;
import PassionIron.Project.GymWebSite.Service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, String> register(@RequestBody RegisterRequestPayload registerRequest) {
        Map<String, String> response = new HashMap<>();

        if (utenteService.findByEmail(registerRequest.getEmail()) != null) {
            response.put("message", "Email già in uso.");
            return response;
        }

        Utente utente = new Utente();
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setUsername(registerRequest.getUsername());
        utente.setEmail(registerRequest.getEmail());
        utente.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        utenteService.createUtente(utente);

        response.put("message", "Registrazione completata.");
        return response;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequestPayload loginRequest) {
        Map<String, Object> response = new HashMap<>();

        // Cerca l'utente per username invece che per email
        Utente utente = utenteService.findByUsername(loginRequest.getUsername());
        if (utente != null && passwordEncoder.matches(loginRequest.getPassword(), utente.getPassword())) {
            String token = jwtTools.createToken(utente);
            String username = utente.getUsername().split("@")[0];
            response.put("token", "Bearer " + token);
            response.put("user", Map.of("username", username));
        } else {
            response.put("message", "Credenziali non valide.");
        }

        return response;
    }


}

