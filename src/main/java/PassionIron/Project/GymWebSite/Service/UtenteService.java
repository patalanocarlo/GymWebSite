package PassionIron.Project.GymWebSite.Service;

import PassionIron.Project.GymWebSite.Entities.Utente;
import PassionIron.Project.GymWebSite.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;


    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }
    public Utente createUtente (Utente utente){
        return utenteRepository.save(utente);
    }
    public Utente findByEmail(String email){
        return utenteRepository.findByEmail(email);
    }
    public Optional<Utente> findById(Long id) {
        return utenteRepository.findById(id);
    }
}
