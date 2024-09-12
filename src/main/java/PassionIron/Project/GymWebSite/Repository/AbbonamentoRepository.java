package PassionIron.Project.GymWebSite.Repository;

import PassionIron.Project.GymWebSite.Entities.Abbonamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbbonamentoRepository extends JpaRepository <Abbonamento,Long>  {
}
