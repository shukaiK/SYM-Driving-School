package com.cmpt.focusdriving.models.Events;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("from Event e where not(e.end < :from or e.start > :to)")
	public List<Event> findBetween(@Param("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
			@Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end);

	// public List<Event> findByInstructor();

	// @Query("from Event e where not(e.end < :from or e.start > :to) and e.username
	// = :username")
	// public List<Event> findBetweenAndUsername(
	// @Param("from") LocalDateTime start,
	// @Param("to") LocalDateTime end,
	// @Param("username") String username);

	// @Query("from Event e where not(e.end < :from or e.start > :to) and
	// e.student.instructor = :instructor")
	// public List<Event> findBetweenByInstructorName(
	// @Param("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
	// @Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end,
	// @Param("instructor") String instructor);

	// @Query("from Event e where not(e.end < :from or e.start > :to) and e.text =
	// :text")
	// public List<Event> findBetweenByInstructorName(
	// @Param("from") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
	// @Param("to") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end,
	// @Param("text") String text);
}
