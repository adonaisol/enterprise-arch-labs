package cs544.cmp1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adonai on 5/31/2017.
 */
public class AppSchoolStud {
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

    public static void main(String[] args) throws Exception{
        setUp();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            Map<String, Stud> studMap = new HashMap();

            studMap.put("A-21", new Stud("A-21", "Adonai", "Solomon"));
            studMap.put("A-27", new Stud("A-27", "Kalieb", "Haile"));
            studMap.put("A-35", new Stud("A-35", "Michael", "Fekadu"));

            School school = new School("MUM");
            school.setStudents(studMap);

            session.persist(school);

            session.getTransaction().commit();
            session.close();
        }

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            List<School> schoolList = session.createQuery("from School ").list();
            for (School school: schoolList) {
                System.out.println("School Name: "+ school.getName());

                school.getStudents().forEach((k,v) ->
                    System.out.println("\tStudent Id: "+ v.getStudentid()+"\tFirst Name: " + v.getFirstname() +
                            "\tLast Name: " + v.getLastname())
                );
            }

            session.getTransaction().commit();
            session.close();
        }
        tearDown();
    }
}
