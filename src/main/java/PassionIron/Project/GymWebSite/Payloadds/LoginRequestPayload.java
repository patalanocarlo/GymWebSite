package PassionIron.Project.GymWebSite.Payloadds;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestPayload {
    private String username;
    private String password;
}
