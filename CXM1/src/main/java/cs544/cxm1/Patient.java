package cs544.cxm1;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "address", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "patient_id", referencedColumnName = "id")
})
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Column(table = "address")
	private String street;
	@Column(table = "address")
	private String zipcode;
	@Column(table = "address")
	private String city;

	public Patient() {

	}

	public Patient(String name, String street, String zipcode, String city) {
		super();
		this.name = name;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getId() {
		return id;
	}

}