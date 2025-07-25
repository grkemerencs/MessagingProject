package com.example.notificationproject.Model.dto.respond;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MessageTemplateRespondDTO {
    private String id;
    private String name;
    private String title_template;
    private String body_template;
    private Set<String> parameters;
}
