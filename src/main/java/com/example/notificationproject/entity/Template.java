package com.example.notificationproject.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;


    @NotBlank
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    @Column(nullable = false)
    private String name;

    @Column(length = 50)
    @NotBlank
    @Size(max = 50, message = "Title template cannot exceed 50 characters")
    private String title_template;

    @Column(length = 100)
    @NotBlank
    @Size(max = 100, message = "Body template cannot exceed 100 characters")
    private String body_template;

}
