package com.example.notificationproject.dto.request;

import com.example.notificationproject.Enum.DevicePlatform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDeviceRequestDTO {
    @NotBlank
    public String ownerName;

    @NotNull
    public DevicePlatform platform;

    @NotBlank
    public String fireBaseToken;


}