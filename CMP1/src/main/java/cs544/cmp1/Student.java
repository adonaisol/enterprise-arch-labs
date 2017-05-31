package cs544.cmp1;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adonai on 5/30/2017.
 */
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Laptop> laptops = new HashSet();

    public Student() {
    }

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(Set<Laptop> laptops) {
        for (Laptop laptop: laptops) {
            laptop.setStudent(this);
        }
        this.laptops = laptops;
    }

    public boolean addLaptop(Laptop laptop){
        laptop.setStudent(this);
        return laptops.add(laptop);
    }

    public boolean removeLaptop(Laptop laptop){
        laptop.setStudent(null);
        return laptops.remove(laptop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (firstname != null ? !firstname.equals(student.firstname) : student.firstname != null) return false;
        return lastname != null ? lastname.equals(student.lastname) : student.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
