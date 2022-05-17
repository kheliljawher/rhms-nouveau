package io.saslab.spring.rhms.repository;

import io.saslab.spring.rhms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByNom(String nom);

    Employee findByPrenom(String prenom);

}
