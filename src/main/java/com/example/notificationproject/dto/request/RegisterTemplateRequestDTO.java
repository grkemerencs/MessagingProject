package com.example.notificationproject.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterTemplateRequestDTO {
    private int id;
    private String name;
    private String title_template;
    private String body_template;
}
