package com.kal.attendanceservice.service;

import com.kal.attendanceservice.model.Attendance;
import com.kal.attendanceservice.model.AttendanceStatistics;
import com.kal.attendanceservice.model.PublicHoliday;
import com.kal.attendanceservice.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private static final String PUBLIC_HOLIDAYS_API_URL = "https://date.nager.at/api/v3/PublicHolidays";

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void checkIn(Long employeeId) {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckIn(LocalDateTime.now());
        attendance.setStatus("CHECKED_IN");

        attendanceRepository.save(attendance);
    }

    public void checkOut(Long employeeId) {
        Optional<Attendance> lastAttendance = attendanceRepository.findTopByEmployeeIdOrderByCheckInDesc(employeeId);
        if (lastAttendance.isPresent()) {
            Attendance attendance = lastAttendance.get();
            attendance.setCheckOut(LocalDateTime.now());
            attendance.setStatus("CHECKED_OUT");

            attendanceRepository.save(attendance);
        }
    }

    public List<Attendance> getAttendanceHistory(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public List<Attendance> getAttendanceHistoryInDateRange(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByEmployeeIdAndCheckInBetween(employeeId, startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
    }

    public AttendanceStatistics getAttendanceStatistics(Long employeeId, LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendanceList = attendanceRepository.findByEmployeeIdAndCheckInBetween(employeeId, startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());

        long totalAttendance = attendanceList.size();
        long totalLateArrivals = attendanceList.stream().filter(attendance -> attendance.getStatus().equals("LATE")).count();
        long totalAbsences = calculateTotalAbsences(startDate, endDate);

        return new AttendanceStatistics(totalAttendance, totalLateArrivals, totalAbsences);
    }

    private List<LocalDate> getPublicHolidaysFromAPI(LocalDate startDate, LocalDate endDate) {
        // Membangun URL API dengan parameter
        String apiUrl = String.format("%s/%d/%s", PUBLIC_HOLIDAYS_API_URL, LocalDate.now().getYear(), "ID");

        // Menggunakan RestTemplate untuk mengambil data dari API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PublicHoliday[]> responseEntity = restTemplate.getForEntity(apiUrl, PublicHoliday[].class);

        // Mengonversi respons API ke dalam List<LocalDate>
        List<LocalDate> publicHolidays = new ArrayList<>();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            PublicHoliday[] holidays = responseEntity.getBody();
            for (PublicHoliday holiday : holidays) {
                LocalDate holidayDate = LocalDate.parse(holiday.getDate());
                if (!holidayDate.isBefore(startDate) && !holidayDate.isAfter(endDate)) {
                    publicHolidays.add(holidayDate);
                }
            }
        }

        return publicHolidays;
    }

    private long calculateTotalAbsences(LocalDate startDate, LocalDate endDate) {
        long totalAbsences = 0;

        List<LocalDate> publicHolidays = getPublicHolidaysFromAPI(startDate, endDate);

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            if (isWeekend(currentDate) || publicHolidays.contains(currentDate)) {
                totalAbsences++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return totalAbsences;
    }

    private boolean isWeekend(LocalDate date) {
        // Anggap Sabtu dan Minggu sebagai hari libur
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
