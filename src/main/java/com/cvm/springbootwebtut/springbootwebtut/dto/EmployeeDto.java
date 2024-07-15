package com.cvm.springbootwebtut.springbootwebtut.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private Long id;

    @NotEmpty(message = "please enter name also")
    private String name;
    @Email(message = "please enter correct email")
    private String email;
    @Max(value = 60,message = "age should be below 60")

    private Integer age;
    private LocalDate dateOfJoining;

}
