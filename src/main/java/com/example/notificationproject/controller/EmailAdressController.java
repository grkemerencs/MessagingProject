package com.example.notificationproject.controller;


import com.example.notificationproject.Model.dto.request.EmailAddressRegisterRequestDTO;
import com.example.notificationproject.Model.dto.respond.ApiRespondDTO;
import com.example.notificationproject.Model.entity.EmailAddress;
import com.example.notificationproject.exception.MongoDuplicateIndexException;
import com.example.notificationproject.service.database.EmailAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("email")
public class EmailAdressController {

    private final EmailAddressService emailAddressService;


    @GetMapping
    public ResponseEntity<ApiRespondDTO<List<EmailAddress>>> getAllEmailAdresses() {
        List<EmailAddress> emailAddresses = emailAddressService.getAllEmailAddresses();
        return ResponseEntity.ok(new ApiRespondDTO<>(emailAddresses));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiRespondDTO<EmailAddress>> registerEmail(@Valid @RequestBody EmailAddressRegisterRequestDTO emailRegisterRequest){
        try {
            EmailAddress emailAddress = emailAddressService.registerEmail(emailRegisterRequest);
            return ResponseEntity.ok(new ApiRespondDTO<>(emailAddress));
        } catch (Exception ex) {
            throw new MongoDuplicateIndexException("The Email "+emailRegisterRequest.getEmailAddress()+ "is already exist");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespondDTO<EmailAddress>> getEmailAddressWithId(@PathVariable String id) {
        EmailAddress emailAddress = emailAddressService.getEmailAddressById(id);
        return ResponseEntity.ok(new ApiRespondDTO<>(emailAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespondDTO<EmailAddress>> deleteEmailAddress(@PathVariable String id) {
        EmailAddress emailAddress = emailAddressService.deleteEmailAddressById(id);
        return ResponseEntity.ok(new ApiRespondDTO<>(emailAddress));
    }

}
