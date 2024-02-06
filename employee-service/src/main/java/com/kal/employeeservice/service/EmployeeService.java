package com.kal.employeeservice.service;

import com.kal.employeeservice.exception.EmployeeNotFoundException;
import com.kal.employeeservice.model.Employee;
import com.kal.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            employee.setHireDate(updatedEmployee.getHireDate());
            employee.setJobTitle(updatedEmployee.getJobTitle());
            employee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(employee);
        } else {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> filterEmployeesByLastName(String lastName) {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public List<String> getAllEmployeeEmails() {
        return employeeRepository.findAll().stream()
                .map(Employee::getEmail)
                .collect(Collectors.toList());
    }

    public double getAverageSalary() {
        OptionalDouble averageSalary = employeeRepository.findAll().stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue())  // Konversi BigDecimal ke double
                .average();
        return averageSalary.orElse(0.0);
    }
}

