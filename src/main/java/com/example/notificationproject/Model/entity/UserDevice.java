package com.example.notificationproject.Model.entity;
import com.example.notificationproject.Enum.DevicePlatform;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Document(collection = "devices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDevice {
    @Id
    private String id;

    @Size(max=20, message="Name cannot exceed 20 characters" )
    @NotBlank(message = "ownerName can't be blank")
    private String ownerName;

    @NotNull(message = "platform can't be blank")
    private DevicePlatform platform;

    @NotBlank(message = "fireBaseToken can't be blank")
    private String fireBaseToken;

    // Constructor with no id field
    public UserDevice(String ownerName, DevicePlatform platform, String fireBaseToken) {
        this.ownerName = ownerName;
        this.platform = platform;
        this.fireBaseToken = fireBaseToken;
    }
}
