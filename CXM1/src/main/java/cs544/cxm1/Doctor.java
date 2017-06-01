package cs544.cxm1;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
/**
 * Created by Adonai on 5/31/2017.
 */
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String doctortype;
    private String firstname;
    private String lastname;

    public Doctor() {
    }

    public Doctor(String doctortype, String firstname, String lastname) {
        this.doctortype = doctortype;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctortype() {
        return doctortype;
    }

    public void setDoctortype(String doctortype) {
        this.doctortype = doctortype;
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
