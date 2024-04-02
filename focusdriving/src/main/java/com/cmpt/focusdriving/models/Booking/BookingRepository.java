package com.cmpt.focusdriving.models.Booking;

import java.util.List;
import java.time.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByBid(int bid);

    List<Booking> findByStartTime(LocalTime startTime);

    List<Booking> findByStudent_InstructorContaining(String instructorName);

    List<Booking> findAll();

}
