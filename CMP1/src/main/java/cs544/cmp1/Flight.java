package cs544.cmp1;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Adonai on 5/30/2017.
 */
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flightnumber;
    @Column(name = "fly_from")
    private String from;
    @Column(name = "fly_to")
    private String to;
    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    public Flight() {
    }

    public Flight(String flightnumber, String from, String to, Date date) {
        this.flightnumber = flightnumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
