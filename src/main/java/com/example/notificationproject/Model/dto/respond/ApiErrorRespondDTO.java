package com.example.notificationproject.Model.dto.respond;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Getter
public class ApiErrorRespondDTO {
    public Boolean success = false;
    public String failureMessage;
    public int errorCode;
}