package cs544.cxm1;

import javax.persistence.*;
/**
 * Created by Adonai on 5/31/2017.
 */
@Embeddable
public class Payment {
    private String paydate;
    private double amount;

    public Payment() {
    }

    public Payment(String paydate, double amount) {
        this.paydate = paydate;
        this.amount = amount;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
