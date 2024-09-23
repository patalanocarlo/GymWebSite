package PassionIron.Project.GymWebSite.Service;

import PassionIron.Project.GymWebSite.Entities.Abbonamento;
import PassionIron.Project.GymWebSite.Repository.AbbonamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.List;
import java.util.Optional;

@Service
public class AbbonamentoService {

    @Autowired
    private AbbonamentoRepository abbonamentoRepository;


    public List<Abbonamento> getAllAbbonamenti() {
        return abbonamentoRepository.findAll();
    }


    public Optional<Abbonamento> getAbbonamentoById(Long id) {
        return abbonamentoRepository.findById(id);
    }


    public Abbonamento createAbbonamento(Abbonamento abbonamento) {
        return abbonamentoRepository.save(abbonamento);
    }

    public Abbonamento updateAbbonamento(Long id, Abbonamento abbonamentoDetails) {
        Optional<Abbonamento> optionalAbbonamento = abbonamentoRepository.findById(id);
        if (optionalAbbonamento.isPresent()) {
            Abbonamento abbonamento = optionalAbbonamento.get();
            abbonamento.setNome(abbonamentoDetails.getNome());
            // Aggiungi altri campi se necessario
            return abbonamentoRepository.save(abbonamento);
        } else {
            return null;
        }
    }


    public void deleteAbbonamento(Long id) {
        abbonamentoRepository.deleteById(id);
    }
}
