package com.example.notificationproject.Model.dto.respond;

import lombok.*;


@Setter
@Getter
public class ApiRespondDTO<T> {
    public Boolean success = true;
    public T object;
    
    public ApiRespondDTO(T object) {
        this.object = object;
    }
}




