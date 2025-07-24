package com.example.notificationproject.Model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "emails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailAddress {
    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)  // veri tabanı performansını nasıl etkiler ?
    String emailAddress;
}
