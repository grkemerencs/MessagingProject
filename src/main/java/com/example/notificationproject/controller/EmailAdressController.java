package com.example.notificationproject.controller;


import com.example.notificationproject.Model.dto.request.RegisterEmailAdressRequestDTO;
import com.example.notificationproject.Model.entity.EmailAdress;
import com.example.notificationproject.service.database.EmailAdressService;
import com.mongodb.MongoWriteException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("email")
public class EmailController {

    private final EmailAdressService emailAdressService;


    @GetMapping
    public ResponseEntity<List<EmailAdress>> getAllEmailAdresses() {
        List<EmailAdress> emailAdresses = emailAdressService.getAllEmailAdresses();
        return ResponseEntity.ok(emailAdresses);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmail(@Valid @RequestBody RegisterEmailAdressRequestDTO emailRegisterRequest){
        try {
            EmailAdress emailAdress = emailAdressService.registerEmail(emailRegisterRequest);
            return ResponseEntity.ok(emailAdress);
        } catch (MongoWriteException ex) {
            return ResponseEntity.badRequest().body("MongoDB write error: " + ex.getError().getMessage());
        }
    }
    // register yaparken aynı e mailden bir tane daha varsa duplicate excep atıyor.
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<String> handleMongoWriteException(MongoWriteException ex) {
        return ResponseEntity.badRequest().body("MongoDB write error: " + ex.getError().getMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailAdress> getEmailAdressWithId(@PathVariable String id) {
        EmailAdress emailAdress = emailAdressService.getEmailAdressById(id);
        return ResponseEntity.ok(emailAdress);
    }

}
