package PassionIron.Project.GymWebSite.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private int telefonoCellulare;
    private String password;

    @ManyToOne
    @JoinColumn(name = "abbonamento_id")
    private Abbonamento abbonamento;
}
