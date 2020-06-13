package com.hemant.beanvalidations.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class Demo {

    @NotNull(message = "id not Null")
    @Range(min = 10000,max = 999999,message = "should be 5-6 digit")
    private Integer id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email
    private String email;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="Mobile number pattern is required is invalid")
    private String moNumber;

    @Past(message = "dob should be past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Min(value = 18,message = "age>=18")
    private int age;
    @AssertTrue(message = "working should be true")
    private boolean working;

}
