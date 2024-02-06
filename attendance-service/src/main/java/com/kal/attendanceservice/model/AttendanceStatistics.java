package com.kal.attendanceservice.model;

public class AttendanceStatistics {

    private long totalAttendance;
    private long totalLateArrivals;
    private long totalAbsences;

    public AttendanceStatistics(long totalAttendance, long totalLateArrivals, long totalAbsences) {
        this.totalAttendance = totalAttendance;
        this.totalLateArrivals = totalLateArrivals;
        this.totalAbsences = totalAbsences;
    }

    // Getter and Setter methods

    public long getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(long totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public long getTotalLateArrivals() {
        return totalLateArrivals;
    }

    public void setTotalLateArrivals(long totalLateArrivals) {
        this.totalLateArrivals = totalLateArrivals;
    }

    public long getTotalAbsences() {
        return totalAbsences;
    }

    public void setTotalAbsences(long totalAbsences) {
        this.totalAbsences = totalAbsences;
    }
}

