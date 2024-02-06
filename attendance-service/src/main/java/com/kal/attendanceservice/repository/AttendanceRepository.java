package com.kal.attendanceservice.repository;

import com.kal.attendanceservice.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeId(Long employeeId);

    Optional<Attendance> findTopByEmployeeIdOrderByCheckInDesc(Long employeeId);

    List<Attendance> findByEmployeeIdAndCheckInBetween(Long employeeId, LocalDateTime startDate, LocalDateTime endDate);

}

