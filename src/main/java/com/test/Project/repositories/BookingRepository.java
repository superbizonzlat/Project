package com.test.Project.repositories;

import com.test.Project.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query("select u from Booking u left join fetch u.sportsField where :date between u.begin_at and u.end_at and u.sportsField.name = :name")
    Optional<Booking> findBookingByDate(@Param("date") LocalDateTime localDateTime, @Param("name") String s);
    //@Query("select u from Booking u  where :date between u.begin_at and u.end_at")
    //Optional<List<Booking>> findBookingByDate(@Param("date") LocalDateTime localDateTime);
}
