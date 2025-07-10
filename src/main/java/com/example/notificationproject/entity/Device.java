package com.example.notificationproject.entity;
import com.example.notificationproject.Enum.DevicePlatform;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.boot.registry.selector.StrategyRegistration;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max=20, message="Name cannot exceed 20 characters" )
    @NotBlank(message = "ownerName can't be blank")
    private String ownerName;

    @NotNull(message = "platform can't be blank")
    @Enumerated(EnumType.STRING) // Enum'u string olarak saklar
    private DevicePlatform platform;

    @NotBlank(message = "fireBaseToken can't be blank")
    private String fireBaseToken;

    // Constructor with no id field
    public Device(String ownerName, DevicePlatform platform, String fireBaseToken) {
        this.ownerName = ownerName;
        this.platform = platform;
        this.fireBaseToken = fireBaseToken;
    }
}
