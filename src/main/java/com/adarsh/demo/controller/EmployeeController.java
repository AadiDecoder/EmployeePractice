package com.adarsh.demo.controller;

import com.adarsh.demo.Model.Employee;
import com.adarsh.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<com.adarsh.demo.Entity.Employee>> getEmployee(){
        List<com.adarsh.demo.Entity.Employee> allEmployee = employeeRepository.findAll();
        List<com.adarsh.demo.Entity.Employee> empReponse = allEmployee.stream().map(emp ->
        {
            com.adarsh.demo.Entity.Employee emp1=new com.adarsh.demo.Entity.Employee();
            emp1.setId(emp.getId());
            emp1.setName(emp.getName());
            emp1.setAddress(emp.getAddress());
            emp1.setAge(emp.getAge());
            return emp1;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(empReponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<com.adarsh.demo.Entity.Employee> addEmployee(@RequestBody Employee employee){
        com.adarsh.demo.Entity.Employee entity = new com.adarsh.demo.Entity.Employee();
        entity.setAddress(employee.getAddress());
        entity.setAge(employee.getAge());
        entity.setName(employee.getName());
        employeeRepository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
