package cs544.cmp1;

import javax.persistence.*;

/**
 * Created by Adonai on 5/30/2017.
 */
@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String type;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    public Laptop() {
    }

    public Laptop(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;

        Laptop laptop = (Laptop) o;

        if (brand != null ? !brand.equals(laptop.brand) : laptop.brand != null) return false;
        if (type != null ? !type.equals(laptop.type) : laptop.type != null) return false;
        return student != null ? student.equals(laptop.student) : laptop.student == null;
    }

    @Override
    public int hashCode() {
        int result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }
}
