package cs544.imp1;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Adonai on 5/31/2017.
 */

@Entity
@DiscriminatorValue("DVD")
public class DVD extends Product{
    private String genre;

    public DVD() {
    }

    public DVD(String name, String description, String genre) {
        super(name, description);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
