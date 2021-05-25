package stripe.payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

public class StripeChargeGit {

    public static void main(String[] args) throws StripeException, JSONException {
    	StripeCharge charge = new StripeCharge();
    	String v = charge.chargeStripe("<stripe_key>", "4111111111111111", 12, 2024, 232, 103.34, "873487");
    	System.out.println("Stripe Transaction id: "+v);
    }
    
    public String chargeStripe(String stripe_key,String card_no,int card_month,int card_year,int card_cvc,double amount, String order) throws StripeException, JSONException {
    	Stripe.apiKey = stripe_key;

        Map<String, Object> card = new HashMap<>();
        card.put("number", card_no);
        card.put("exp_month", card_month);
        card.put("exp_year", card_year);
        card.put("cvc", card_cvc);
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);

        Token token = Token.create(params);
        System.out.println(token);

        JSONObject jsonObj = new JSONObject(token);
        String id = jsonObj.getString("id");
        System.out.println(id);
        double  amt = amount;
        amt = amt * 100;
        int namt = (int)amt;
        System.out.println(namt);
        Map<String, Object> chargeMap = new HashMap<>();
        chargeMap.put("amount", namt);
        chargeMap.put("currency", "usd");
        chargeMap.put("source", id);
        chargeMap.put(
          "description",
          "Charged for Order #"+order
        );
        
        try {
        	Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
            JSONObject jsonObj2 = new JSONObject(charge);
            String tid = jsonObj2.getString("id");
            return tid;
        } catch (StripeException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}