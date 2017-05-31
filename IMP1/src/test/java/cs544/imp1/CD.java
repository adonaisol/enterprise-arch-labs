package cs544.imp1;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
@DiscriminatorValue("CD")
public class CD extends Product{
     private String artist;

    public CD() {
    }

    public CD(String name, String description, String artist) {
        super(name, description);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
