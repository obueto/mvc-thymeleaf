package com.example.demo.Thymeleaf.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Validated
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Users {
    @Id
    @Column(
            name ="id",nullable = false)
    @SequenceGenerator(
       name="user_id_sequence",
       sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    private Long id;

    @NotBlank(message = "name property cannot be blank")
    private String name;

    @NotBlank(message = "email property cannot be blank")
    @Email
    private String email;
}
