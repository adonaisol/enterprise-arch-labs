package cs544.hpa1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppCar {

    private static SessionFactory sessionFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
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

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Owner owner = new Owner("Adonai", "Fairfield, IA");
            Owner owner2 = new Owner("Kalieb", "Sacramento, CA");

            // Create new instance of Car and set values in it
            Car car1 = new Car("BMW", "SDA231", 30221.00, owner);
            // save the car
            session.persist(car1);
            // Create new instance of Car and set values in it
            Car car2 = new Car("Mercedes", "HOO100", 4088.00, owner2);


            // save the car
            session.persist(car2);

            session.getTransaction().commit();
            session.close();
        }

        try (Session session  = sessionFactory.openSession()) {
            session.beginTransaction();

            // retieve all cars
            @SuppressWarnings("unchecked")
            List<Car> carList = session.createQuery("from Car").list();
            for (Car car : carList) {
                System.out.println("brand= " + car.getBrand() + ", year= "
                        + car.getYear() + ", price= " + car.getPrice() + ", owner= "
                        + car.getOwner().getName() + ", address= " + car.getOwner().getAddress());
            }
            session.getTransaction().commit();
        }

        // Close the SessionFactory (best practice)
        tearDown();
    }
}

