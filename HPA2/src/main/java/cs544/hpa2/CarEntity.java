package cs544.hpa2;

import javax.persistence.*;

/**
 * Created by Adonai on 5/30/2017.
 */
@Entity
@Table(name = "car", schema = "cs544", catalog = "")
public class CarEntity {
    private long id;
    private String brand;
    private double price;
    private String year;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (id != carEntity.id) return false;
        if (Double.compare(carEntity.price, price) != 0) return false;
        if (brand != null ? !brand.equals(carEntity.brand) : carEntity.brand != null) return false;
        if (year != null ? !year.equals(carEntity.year) : carEntity.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
