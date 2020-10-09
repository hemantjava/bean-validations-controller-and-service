package com.hemant.beanvalidations.service;


import com.hemant.beanvalidations.bean.Demo;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ValidationExample {

    public static void main (String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Demo demo = Demo.builder()
                .age(17)
                .working(true)
                .moNumber("909881653")
                .dob(LocalDate.of(2000,06,22))
                .email("hemant@gmail.com")
                .name("Hemant")
                .id(1212)
                .build();
        Set<ConstraintViolation<Demo>> violations = validator.validate(demo);
        for (ConstraintViolation<Demo> violation : violations) {
            log.error(violation.getPropertyPath().toString()+"="+violation.getMessage());
        }
        final Map<String, String> violationsMap = new LinkedHashMap<>();
        violations.forEach(error->{
            violationsMap.put(error.getPropertyPath().toString(),error.getMessage());
        });

        System.out.println(violationsMap);
    }

}
