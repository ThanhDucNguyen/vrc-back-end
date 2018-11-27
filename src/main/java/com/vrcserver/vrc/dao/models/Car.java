package com.vrcserver.vrc.dao.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "car_model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private String yearCar;

    @Column(name = "price_default")
    private Integer price;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private Set<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "type_car_id")
    private TypeCar typeCar;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private CarOwner carOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public TypeCar getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(TypeCar typeCar) {
        this.typeCar = typeCar;
    }

    public CarOwner getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwner carOwner) {
        this.carOwner = carOwner;
    }

    public String getYearCar() {
        return yearCar;
    }

    public void setYearCar(String yearCar) {
        this.yearCar = yearCar;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
