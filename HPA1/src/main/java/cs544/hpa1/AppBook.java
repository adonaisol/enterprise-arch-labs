package cs544.hpa1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by Adonai on 5/30/2017.
 */
public class AppBook {
    private static SessionFactory sessionFactory;

    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try{
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch(Exception e){
           e.printStackTrace();
           StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    protected static void tearDown() throws Exception{
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }

    public static void main(String[] args) throws Exception{
        setUp();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book book1 = new Book("Shiver", "B2920", "Cassandra Claire", 23.50, Date.from(Instant.now()));
            Book book2 = new Book("Black Hawk Down", "B3456", "Mark Bowden", 47.29, Date.from(Instant.now()));
            Book book3 = new Book("Serpentine", "A2323", "Mark Bowden", 22.96, Date.from(Instant.now()));

            session.persist(book1);
            session.persist(book2);
            session.persist(book3);

            session.getTransaction().commit();
        }

        retrieveAll();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Book where author = 'Mark Bowden'");
            //query.setParameter("auth","")
            Book book = (Book)query.list().get(1);
            System.out.println("Title: " +book.getTitle()+
                    "\tAuthor: " +book.getAuthor()+
                    "\tPrice: " +book.getPrice()+
                    "\tISBN: " +book.getISBN());

            book.setTitle("Zero Dark Thirty");
            book.setPrice(34.64);
            session.update(book);
            session.delete(session.createQuery("from Book where author = 'Mark Bowden'").list().get(0));

            session.getTransaction().commit();
        }

        retrieveAll();

        tearDown();
    }

    static void retrieveAll(){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();

            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList){
                System.out.println("Title: " +book.getTitle()+
                        "\tAuthor: " +book.getAuthor()+
                        "\tPrice: " +book.getPrice()+
                        "\tISBN: " +book.getISBN());
            }
            session.getTransaction().commit();
        }
    }
}
