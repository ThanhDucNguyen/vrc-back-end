package com.vrcserver.vrc.dao.repositories;

import com.vrcserver.vrc.dao.models.Car;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "select * from car as s "+
            "inner join type_car as y on s.type_car_id = y.type_car_id"+
            " WHERE s.type like %?1% and s.year like %?2% and s.car_model like %?3% and  y.name like %?4%",nativeQuery = true)
    List<Car> getCar( String type, String year,String model, String name);
}
