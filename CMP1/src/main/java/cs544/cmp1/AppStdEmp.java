package cs544.cmp1;

/**
 * Created by Adonai on 5/30/2017.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppStdEmp {

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

            Laptop[] lp = {new Laptop("Dell", "Latitude"),
                            new Laptop("Lenovo", "ThinkPad")};
            Set<Laptop> laptopSet = new HashSet(Arrays.asList(lp));

            Student st1 = new Student("Adonai", "Solomon");

            st1.setLaptops(laptopSet);
            st1.addLaptop(new Laptop("HP", "Envy"));

            Laptop newLp = new Laptop("Toshiba","Satellite");
            newLp.setStudent(new Student("Kalieb", "Hailu"));
//            System.out.println(st1.getLaptops().toString());
//            System.out.println(st1.getLaptops().stream().findFirst().get().getStudent().getFirstname());
            session.persist(st1);
            session.persist(newLp);
            session.getTransaction().commit();
            session.close();
        }

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            List<Student> studentList = session.createQuery("from Student").list();
            for (Student student: studentList) {
                System.out.println("Student Name: "+ student.getFirstname() + " " + student.getLastname());
                for (Laptop laptop: student.getLaptops())
                    System.out.println("\tBrand: "+ laptop.getBrand()+"\tType: " + laptop.getType());
            }

            session.getTransaction().commit();
            session.close();
        }
        tearDown();
    }
}
