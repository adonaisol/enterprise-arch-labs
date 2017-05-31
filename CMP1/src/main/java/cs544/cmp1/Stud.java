package cs544.cmp1;

import javax.persistence.*;

/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
public class Stud {
    @Id
    private String studentid;
    private String firstname;
    private String lastname;

    public Stud() {
    }

    public Stud(String studentid, String firstname, String lastname) {
        this.studentid = studentid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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
}
