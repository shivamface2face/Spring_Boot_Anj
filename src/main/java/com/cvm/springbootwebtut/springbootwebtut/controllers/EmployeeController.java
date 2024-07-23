package com.cvm.springbootwebtut.springbootwebtut.controllers;


import com.cvm.springbootwebtut.springbootwebtut.dto.EmployeeDto;
import com.cvm.springbootwebtut.springbootwebtut.entities.EmployeeEntity;
import com.cvm.springbootwebtut.springbootwebtut.reposittories.EmployeeRepositry;
import com.cvm.springbootwebtut.springbootwebtut.services.EmployeeServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class EmployeeController {



//    @GetMapping("employee/{emplId}")
//    public EmployeeDto getEmployeeById(@PathVariable long emplId){
//        return  new EmployeeDto(emplId,"cvm","cvm@123.com",24, LocalDate.now(),true,"SDE",3.6);
//    }
//
//     @GetMapping("employee")
//    public  String getAllEmployee(@RequestParam(required = false) Integer age){
//         return  "hi"+age;
//    }
//
//
//    @PostMapping ("/employee")
//    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto inputEmpl){
//         inputEmpl.setId(23L);
//         return inputEmpl;
//
//    }


//    @PutMapping("/employee")
//    public String  updateEmployee(){
//         return "hi its updated";
//    }
//
//
//    private  final EmployeeRepositry employeeRepositry;
//
//    public EmployeeController(EmployeeRepositry employeeRepositry) {
//        this.employeeRepositry = employeeRepositry;
//    }
//
//
//    @GetMapping("employee/{emplId}")
//    public EmployeeEntity getEmployeeById(@PathVariable long emplId){
//        return  employeeRepositry.findById(emplId).orElse(null);
//    }
//
//
//    @GetMapping("employee")
//    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Integer age){
//        return employeeRepositry.findAll();
//    }
//
//
//    @PostMapping ("/employee")
//    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmpl){
//      return employeeRepositry.save(inputEmpl);
//
//    }


     // controller dont connect direct with the entity so now we will make who will connect with entity in controller we will deal wtih DTO layer


    @PutMapping("/employee")
    public String  updateEmployee(){
        return "hi its updated";
    }




    private  final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;

    }


    @GetMapping("employee/{emplId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long emplId){
     Optional<EmployeeDto> employeeDto= Optional.ofNullable(employeeServices.getEmployeeById(emplId));
      return  employeeDto
              .map(employeeDto1->ResponseEntity.ok(employeeDto1))
              .orElseThrow(()->new NoSuchElementException("employee not found"));
    }

    @ExceptionHandler
    public  ResponseEntity<String> handelEmployeeNotFound (NoSuchElementException exception){
   return  new ResponseEntity<>("employee not found ", HttpStatus.NOT_FOUND);
    }




    @GetMapping("employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(employeeServices.getAllEmployee());
    }


    @PostMapping ("/employee")
    public EmployeeDto createNewEmployee(@RequestBody @Valid EmployeeDto inputEmpl){
        return employeeServices.createNewEmployee(inputEmpl);

    }

    @PutMapping("/employee/{emlId}")
    public EmployeeDto updateEmployeeById(@RequestBody EmployeeDto employeeDto,@PathVariable Long emplId){
        return  employeeServices.updateEmployeeById(employeeDto,emplId);
    }

    @DeleteMapping("/employee/{emplId}")
    public void deleteById(@PathVariable Long emplId){
        employeeServices.deleteById(emplId);
    }

    // for partial update we have patch

    @PatchMapping("/employee/{emlId}")
    public EmployeeDto updatePartialEmployeeById(@RequestBody Map<String ,Object> updates, @PathVariable Long emplId){
        return  employeeServices.updatePartialEmployeeById(updates,emplId);
    }


}
