package cs544.cxm1;

import javax.persistence.*;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String appdate;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="patient")
	private Patient patient;
	@Embedded
	private Payment payment;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="doctor")
	private Doctor doctor;
	public Appointment(){

	}
	public Appointment(String appdate, Patient patient, Payment payment, Doctor doctor) {
		super();
		this.appdate = appdate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}
	public String getAppdate() {
		return appdate;
	}
	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public long getId() {
		return id;
	}

}
