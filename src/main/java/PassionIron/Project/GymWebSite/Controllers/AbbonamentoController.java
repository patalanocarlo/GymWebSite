package PassionIron.Project.GymWebSite.Controllers;



import PassionIron.Project.GymWebSite.Entities.Abbonamento;


import PassionIron.Project.GymWebSite.Repository.AbbonamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abbonamenti")
public class AbbonamentoController {
    @Value("${stripe_secret_key}")
    private String stripeApiKey;
    @Autowired
    private AbbonamentoRepository abbonamentoRepository;

    @GetMapping
    public List<Abbonamento> getAllAbbonamenti() {
        return abbonamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Abbonamento getAbbonamentoById(@PathVariable Long id) {
        return abbonamentoRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Abbonamento createAbbonamento(@RequestBody Abbonamento abbonamento) {
        return abbonamentoRepository.save(abbonamento);
    }

    @PutMapping("/{id}")
    public Abbonamento updateAbbonamento(@PathVariable Long id, @RequestBody Abbonamento abbonamentoDetails) {
        Abbonamento abbonamento = abbonamentoRepository.findById(id).orElse(null);
        if (abbonamento != null) {
            abbonamento.setNome(abbonamentoDetails.getNome());
            // Aggiungi altri campi se necessario
            return abbonamentoRepository.save(abbonamento);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAbbonamento(@PathVariable Long id) {
        abbonamentoRepository.deleteById(id);
    }

    }

