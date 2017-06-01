package cs544.cxm1;

/**
 * Created by Adonai on 6/1/2017.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


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

            Patient patient = new Patient("Adonai", "1000 N 4th St", "52557", "Fairfield");
            Payment payment = new Payment("06/01/2017", 154.90);
            Doctor doctor = new Doctor("GP", "Philip", "Trent");
            Appointment app = new Appointment("06/10/2017", patient, payment, doctor);

            session.persist(app);
            session.getTransaction().commit();
            session.close();
        }

        tearDown();
    }
}
