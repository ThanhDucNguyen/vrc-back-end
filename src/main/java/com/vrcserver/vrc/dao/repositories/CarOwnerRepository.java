package com.vrcserver.vrc.dao.repositories;

import com.vrcserver.vrc.dao.models.CarOwner;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
    @Query(value = "select *from car_owner where owner_id =?1", nativeQuery = true)
    CarOwner findCarOwnerById(Long id);
}
