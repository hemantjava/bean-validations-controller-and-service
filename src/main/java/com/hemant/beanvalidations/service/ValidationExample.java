package com.hemant.beanvalidations.service;


import com.hemant.beanvalidations.bean.Demo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

@Log4j2
public class ValidationExample {

    public static void main (String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Demo demo = Demo.builder()
                .age(18)
                .working(true)
                .moNumber("9098816532")
                .dob(LocalDate.of(2000,06,22))
                .email("hemant@gmail.com")
                .name("Hemant")
                .id(1212)
                .build();
        Set<ConstraintViolation<Demo>> violations = validator.validate(demo);

        System.out.println(violations.isEmpty());
        for (ConstraintViolation<Demo> violation : violations) {
            log.error(violation.getMessage());
        }
    }

}
