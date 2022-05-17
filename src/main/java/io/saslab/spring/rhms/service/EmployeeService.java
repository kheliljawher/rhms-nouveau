package io.saslab.spring.rhms.service;

import io.saslab.spring.rhms.entity.Employee;
import io.saslab.spring.rhms.exception.ResourceNotFoundException;
import io.saslab.spring.rhms.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);





    }

    public List<Employee> getAllEmployees(List<Employee> employee) {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

       // return employeeRepository.findById(id).orElse(null);
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new ResourceNotFoundException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    public Employee getEmployeeByNom(String nom) {
        return employeeRepository.findByNom(nom);
    }

    public Employee getEmployeeByPrenom(String prenom) {
        return employeeRepository.findByPrenom(prenom);
    }

    public ResponseEntity<Object> updateEmployee(Long id, Employee employee) {

        employeeRepository
                .findById(id);
               // .orElseThrow(()-> new ResourceNotFoundException(id));
        employee.setId(id);
        employee.setNom(employee.getNom());
        employee.setPrenom(employee.getPrenom());
       // employee.setEmail(employee.getEmail());
        employee.setLogin(employee.getLogin());
        employee.setPassword(employee.getPassword());
        employee.setNum_cin(employee.getNum_cin());
        employee.setNum_tel(employee.getNum_tel());

        //  employee.setAdresse(employee.getAdresse());
        employee.setImage(employee.getImage());
        return ResponseEntity.accepted().body("Successfully updated Employee");

    }

    public void deleteEmployeeById(long id) {

        Employee emp =
                employeeRepository
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employeeRepository.delete(emp);
    }

}
