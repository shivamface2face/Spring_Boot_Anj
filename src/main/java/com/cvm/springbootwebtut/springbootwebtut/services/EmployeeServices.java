package com.cvm.springbootwebtut.springbootwebtut.services;


import com.cvm.springbootwebtut.springbootwebtut.dto.EmployeeDto;
import com.cvm.springbootwebtut.springbootwebtut.entities.EmployeeEntity;
import com.cvm.springbootwebtut.springbootwebtut.reposittories.EmployeeRepositry;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServices {


    private  final EmployeeRepositry employeeRepositry;
    private  final ModelMapper mapper;

    public EmployeeServices(EmployeeRepositry employeeRepositry, ModelMapper mapper) {
        this.employeeRepositry = employeeRepositry;

        this.mapper = mapper;
    }


    public EmployeeDto getEmployeeById(long emplId) {
      EmployeeEntity employeeEntity  = employeeRepositry.findById(emplId).orElse(null);

        return mapper.map(employeeEntity,EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeEntity>employeeEntities=employeeRepositry.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmpl) {
       EmployeeEntity employeeEntity=mapper.map(inputEmpl,EmployeeEntity.class);
       EmployeeEntity saveEmployeeEntity=employeeRepositry.save(employeeEntity);
       return  mapper.map(saveEmployeeEntity,EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long emplId) {
        EmployeeEntity employeeEntity=mapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(emplId);
        EmployeeEntity esaveEmployeEntity=employeeRepositry.save(employeeEntity);
        return  mapper.map(esaveEmployeEntity,EmployeeDto.class);

    }

    public void deleteById(Long emplId) {
        employeeRepositry.deleteById(emplId);
    }
    public boolean isExist(long id){
  return employeeRepositry.existsById(id);
    }


    public EmployeeDto updatePartialEmployeeById(Map<String, Object> updates, Long emplId) {
        boolean exist=isExist(emplId);
        if(!exist){
            return null;
        }
        EmployeeEntity employeeEntity=employeeRepositry.findById(emplId).get();
        updates.forEach((feild,value)->{
            Field fieldToUpdate= ReflectionUtils.findField(EmployeeEntity.class,feild);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate,employeeEntity,value);
        });
        return mapper.map(employeeRepositry.save(employeeEntity),EmployeeDto.class);
    }
}
