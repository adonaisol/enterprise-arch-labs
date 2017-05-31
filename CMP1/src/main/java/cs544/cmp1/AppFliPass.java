package cs544.cmp1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Adonai on 5/30/2017.
 */
public class AppFliPass {

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

            Flight[] fl = {new Flight("B210", "Chicago", "Cedar Rapids", Date.valueOf("2017-5-31")),
                    new Flight("AA234", "Baltimore", "Chicago", Date.valueOf("2017-5-31"))};

            Passenger p1 = new Passenger("Adonai Solomon", Arrays.asList(fl));

            session.persist(p1);

            session.getTransaction().commit();
            session.close();
        }
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            List<Passenger> passList = session.createQuery("from Passenger ").list();
            for (Passenger pass: passList) {
                System.out.println("Passenger Name: "+ pass.getName());
                for (Flight flight: pass.getFlights())
                    System.out.println("\tFlight Number: "+ flight.getFlightnumber()+"\tFrom: " + flight.getFrom() +
                                        "\tTo: " +flight.getTo() + "\tDate: " +flight.getDate());
            }

            session.getTransaction().commit();
            session.close();
        }
        tearDown();
    }
}

