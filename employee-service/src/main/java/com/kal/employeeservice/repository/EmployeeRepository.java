package com.kal.employeeservice.repository;

import com.kal.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Tambahan method sesuai kebutuhan
}

