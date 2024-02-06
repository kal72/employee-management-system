package com.kal.attendanceservice.controller;

import com.kal.attendanceservice.model.Attendance;
import com.kal.attendanceservice.model.AttendanceStatistics;
import com.kal.attendanceservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/check-in/{employeeId}")
    public ResponseEntity<Void> checkIn(@PathVariable Long employeeId) {
        attendanceService.checkIn(employeeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/check-out/{employeeId}")
    public ResponseEntity<Void> checkOut(@PathVariable Long employeeId) {
        attendanceService.checkOut(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceHistory(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Attendance> attendanceHistory;
        if (startDate == null || endDate == null) {
            attendanceHistory = attendanceService.getAttendanceHistory(employeeId);
        } else {
            attendanceHistory = attendanceService.getAttendanceHistoryInDateRange(employeeId, startDate, endDate);
        }

        return ResponseEntity.ok(attendanceHistory);
    }

    @GetMapping("/statistics/{employeeId}")
    public ResponseEntity<AttendanceStatistics> getAttendanceStatistics(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        AttendanceStatistics attendanceStatistics = attendanceService.getAttendanceStatistics(employeeId, startDate, endDate);
        return ResponseEntity.ok(attendanceStatistics);
    }
}

