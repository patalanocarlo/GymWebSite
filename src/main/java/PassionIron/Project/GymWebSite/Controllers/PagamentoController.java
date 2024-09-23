package PassionIron.Project.GymWebSite.Controllers;



import PassionIron.Project.GymWebSite.Payloadds.PagamentoRequest;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamenti")
public class PagamentoController {

    @Value("${stripe_secret_key}")
    private String stripeApiKey;

    @PostMapping("/checkout")
    public String checkout(@RequestBody PagamentoRequest request) {
        Stripe.apiKey = stripeApiKey;

        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(request.getAmount())
                    .setCurrency("eur")
                    .setSource(request.getStripeToken()) // Il token generato dal frontend
                    .setDescription("Pagamento per il piano ID: " + request.getPianoId())
                    .build();

            Charge charge = Charge.create(params);
            return charge.getId(); // Restituisce l'ID del pagamento

        } catch (Exception e) {
            e.printStackTrace();
            return "Errore: " + e.getMessage();
        }
    }
}
