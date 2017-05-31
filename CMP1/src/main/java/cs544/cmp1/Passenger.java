package cs544.cmp1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adonai on 5/30/2017.
 */
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList();

    public Passenger() {
    }

    public Passenger(String name, List<Flight> flights) {
        this.name = name;
        this.flights = flights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

}
