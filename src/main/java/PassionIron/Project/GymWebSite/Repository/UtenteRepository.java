package PassionIron.Project.GymWebSite.Repository;

import PassionIron.Project.GymWebSite.Entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository <Utente,Long> {
    Utente findByEmail(String email);
}
