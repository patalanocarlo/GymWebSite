package PassionIron.Project.GymWebSite.Payloadds;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestPayload {
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
