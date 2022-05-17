package io.saslab.spring.rhms.controller;

import io.saslab.spring.rhms.entity.Employee;
import io.saslab.spring.rhms.repository.EmployeeRepository;
import io.saslab.spring.rhms.service.EmployeeService;
import io.saslab.spring.rhms.service.StorageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.util.List;

@RestController
@RequestMapping(value = "v1/employee")
@CrossOrigin(origins = "http://localhost:4200/")
@Tag(name = "Employee", description = "CRUD employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StorageService storageService;


    @GetMapping("/")

    public String getMessage() {
        return "Employee controller ...";
    }

    @PostMapping("/employee/create")
    @ApiOperation(value = "Create an employee", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully add an employees"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )

   /* public Employee createEmployee(@RequestBody Employee employee) {
        return  EmployeeRepository.save(employee);*/

    public ResponseEntity<Employee> create(@RequestParam("file") MultipartFile file, Employee employee) {
        try {
            String filename = storageService.fileName(file);
            storageService.store(file,filename);
            employee.setImage(filename);
            employeeService.addEmployee(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/getEmployeesList")
    @ApiOperation(value = "View all employees", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all employees"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public List<Employee> findAllEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/getEmployeeById/{id}")
    @ApiOperation(value = "View employee by id", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all employee by id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Employee findEmployeeById(@PathVariable long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/getEmployeeByNom/{nom}")
    @ApiOperation(value = "View employee by nom", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee by nom"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Employee findEmployeeBynom(@PathVariable String nom){
        return employeeService.getEmployeeByNom(nom);
    }

    @GetMapping("/getEmployeeByPrenom/{prenom}")
    @ApiOperation(value = "View employee by prenom", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee by prenom"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public Employee findEmployeeByprenom(@PathVariable String prenom){
        return employeeService.getEmployeeByPrenom(prenom);
    }

    @PutMapping("/employees/update/{id}")
    @ApiOperation(value = "update an existing employees", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update an employees"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )
    public ResponseEntity<Employee> update (@RequestBody Employee employee,@PathVariable Long id){

        try {
            employee.setId(id);
            employeeService.addEmployee(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_ACCEPTABLE);
        }    }

    @DeleteMapping("/deleteEmployees/{id}")
    @ApiOperation(value = "Deletes specific employee with the supplied employee id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deletes the specific employee"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    }
    )

    public void deleteEmployeeById(@PathVariable @Parameter(description = "The id of the employee to delete.")long id)  {

        employeeService.deleteEmployeeById(id);

    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
