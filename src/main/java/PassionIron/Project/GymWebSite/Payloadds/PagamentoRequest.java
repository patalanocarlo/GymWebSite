package PassionIron.Project.GymWebSite.Payloadds;

public class PagamentoRequest {
    private String stripeToken;
    private Long pianoId;
    private Long amount;

    // Getters e setters
    public String getStripeToken() {
        return stripeToken;
    }

    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

    public Long getPianoId() {
        return pianoId;
    }

    public void setPianoId(Long pianoId) {
        this.pianoId = pianoId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
