package com.vrcserver.vrc.dao.repositories;

import com.vrcserver.vrc.dao.models.Booking;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "select *from booking where booking_id =?1", nativeQuery = true)
    Booking findBookingById(Long id);
}
