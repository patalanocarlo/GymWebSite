package PassionIron.Project.GymWebSite.Service;

import PassionIron.Project.GymWebSite.Entities.Abbonamento;
import PassionIron.Project.GymWebSite.Repository.AbbonamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbbonamentoService {

 @Autowired
    private AbbonamentoRepository abbonamentoRepository;

 public Abbonamento createAbbonamento( Abbonamento abbonamento){
     return abbonamentoRepository.save(abbonamento);
 }

    public Abbonamento getAbbonamentoById(Long id) {
        return abbonamentoRepository.findById(id).orElse(null);
    }

}
