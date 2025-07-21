package com.example.notificationproject.Model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterMessageTemplateRequestDTO {
    private String name;
    private String title_template;
    private String body_template;
}
