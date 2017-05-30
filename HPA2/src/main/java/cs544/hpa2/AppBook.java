package cs544.hpa2;


import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by Adonai on 5/30/2017.
 */
public class AppBook {
    //private static SessionFactory sessionFactory;
    private static EntityManagerFactory entityManagerFactory;

    protected static void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");

    }

    protected static void tearDown() throws Exception{
        entityManagerFactory.close();
    }

    public static void main(String[] args) throws Exception{
        setUp();

        EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            Book book1 = new Book("Shiver", "B2920", "Cassandra Claire", 23.50, Date.from(Instant.now()));
            Book book2 = new Book("Black Hawk Down", "B3456", "Mark Bowden", 47.29, Date.from(Instant.now()));
            Book book3 = new Book("Serpentine", "A2323", "Mark Bowden", 22.96, Date.from(Instant.now()));

            em.persist(book1);
            em.persist(book2);
            em.persist(book3);

            em.getTransaction().commit();

        retrieveAll();

        //EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            Query query = em.createQuery("from Book where author = 'Mark Bowden'");
            //query.setParameter("auth","")
            Book book = (Book)query.getResultList().get(1);
            System.out.println("Title: " +book.getTitle()+
                    "\tAuthor: " +book.getAuthor()+
                    "\tPrice: " +book.getPrice()+
                    "\tISBN: " +book.getISBN());

            book.setTitle("Zero Dark Thirty");
            book.setPrice(34.64);
            em.merge(book);
            em.remove(em.createQuery("from Book where author = 'Mark Bowden'").getResultList().get(0));

            em.getTransaction().commit();


        retrieveAll();

        tearDown();
    }

    static void retrieveAll(){
        EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            List<Book> bookList = em.createQuery("from Book").getResultList();
            for (Book book : bookList){
                System.out.println("Title: " +book.getTitle()+
                        "\tAuthor: " +book.getAuthor()+
                        "\tPrice: " +book.getPrice()+
                        "\tISBN: " +book.getISBN());
            }
            em.getTransaction().commit();

    }
}
