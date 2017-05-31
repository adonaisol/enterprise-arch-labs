package cs544.imp1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList();

    public Customer() {
    }

    public Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Order> getOrder() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }

    public boolean addOrder(Order order) {
        order.setCustomer(this);
        return orders.add(order);
    }

    public boolean removeOrder(Order order) {
        order.setCustomer(null);
        return orders.add(order);
    }
}
