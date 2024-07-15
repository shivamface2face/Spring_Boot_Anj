package com.cvm.springbootwebtut.springbootwebtut.reposittories;

import com.cvm.springbootwebtut.springbootwebtut.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositry extends JpaRepository<EmployeeEntity,Long> {

}
