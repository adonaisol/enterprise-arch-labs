package cs544.imp1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
@Table(name = "books")
@DiscriminatorValue("Book")
public class Book extends Product{
    private String title;

    public Book() {
    }

    public Book(String name, String description, String title) {
        super(name, description);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
