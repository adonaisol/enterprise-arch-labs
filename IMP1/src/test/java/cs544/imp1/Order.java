package cs544.imp1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue
    private int orderid;
    private java.util.Date date;
    @ManyToOne
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> orderlines = new ArrayList<>();

    public Order() {
    }

    public Order(Date date) {
        this.date = date;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderline() {
        return orderlines;
    }

    public void setOrderline(List<OrderLine> orderline) {
        this.orderlines = orderline;
    }

    public void addOrderline(OrderLine orderLine) {
        this.orderlines.add(orderLine);
    }

    public void removeOrderline(OrderLine orderLine) {
         orderlines.add(orderLine);
    }
}
