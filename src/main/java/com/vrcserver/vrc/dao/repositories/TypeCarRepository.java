package com.vrcserver.vrc.dao.repositories;

import com.vrcserver.vrc.dao.models.TypeCar;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeCarRepository extends JpaRepository<TypeCar, Long> {
}
