package com.vrcserver.vrc.dao.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "type_car")
public class TypeCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_car_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "typeCar", fetch = FetchType.EAGER)
    private Set<Car> cars;

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
