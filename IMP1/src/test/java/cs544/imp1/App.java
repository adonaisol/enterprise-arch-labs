package cs544.imp1;

/**
 * Created by Adonai on 5/31/2017.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.util.Arrays;

public class App {
    private static SessionFactory sessionFactory;

    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected static void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) throws Exception {
        setUp();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            Product product = new Product("Caffe Latte", "A fine blend of whole milk and Ethiopian coffee");
            Product product1 = new Product("Americano", "Strong, two shots of concentrated fine Brazilian");
            Product product2 = new Product("Coffee", "Regular black coffee");

            CD p1 = new CD("Hybrid Theory", "Their best selling album to date", "Linkin Park");
            DVD p2 = new DVD("The Circle", "A great movie about how technology is taking over our lives", "Drama");
            Book p3 = new Book("Core Java", "All the core element you need to know about Java", "Ray Wilkins");

            OrderLine[] orderLines = {new OrderLine(3, p1), new OrderLine(2, p2),
                    new OrderLine(1, product2), new OrderLine(1, p3)};

            Order order = new Order(Date.valueOf("2017-5-25"));
            order.setOrderline(Arrays.asList(orderLines));

            OrderLine orderLine = new OrderLine(1, product2);
            //order.addOrderline(orderLine);

            Customer customer = new Customer("Adonai", "Solomon");
            customer.addOrder(order);

            session.persist(customer);
            session.getTransaction().commit();
            session.close();
        }
        tearDown();
    }
}
