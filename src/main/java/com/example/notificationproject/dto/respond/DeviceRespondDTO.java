package com.example.notificationproject.dto.respond;

import com.example.notificationproject.Enum.DevicePlatform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceRespondDTO {

    public String id;
    public String ownerName;
    public DevicePlatform platform;

}
