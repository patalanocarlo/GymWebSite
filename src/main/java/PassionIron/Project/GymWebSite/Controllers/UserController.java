package PassionIron.Project.GymWebSite.Controllers;

import PassionIron.Project.GymWebSite.Entities.Utente;
import PassionIron.Project.GymWebSite.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/me")
    public Utente getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Utente utente = utenteService.findByEmail(userDetails.getUsername());
        if (utente != null) {
            return utente;
        } else {

            return null;
        }
    }


}
