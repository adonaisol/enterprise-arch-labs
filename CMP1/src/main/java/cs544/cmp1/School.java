package cs544.cmp1;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name="studentid")
    private Map<String, Stud> students = new HashMap();
    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Stud> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Stud> students) {
        this.students = students;
    }
}
