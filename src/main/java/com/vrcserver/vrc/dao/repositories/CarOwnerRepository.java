package com.vrcserver.vrc.dao.repositories;

import com.vrcserver.vrc.dao.models.CarOwner;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
}
