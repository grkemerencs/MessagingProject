package com.example.notificationproject.Model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Document(collection = "templates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageTemplate {
    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)  // aynı isimde birden fazla kayıt olmasın, name e göre query atıcam.
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    private String name;

    @NotBlank
    @Size(max = 50, message = "Title template cannot exceed 50 characters")
    private String title_template;

    @NotBlank
    @Size(max = 100, message = "Body template cannot exceed 100 characters")
    private String body_template;
}
