package com.example.notificationproject.controller;


import com.example.notificationproject.dto.request.RegisterEmailRequestDTO;
import com.example.notificationproject.entity.Email;
import com.example.notificationproject.service.database.EmailService;
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

    private final EmailService emailService;


    @GetMapping
    public ResponseEntity<List<Email>> getAllEmails() {
        List<Email> emails = emailService.getAllEmailEntities();
        return ResponseEntity.ok(emails);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDevice(@Valid @RequestBody RegisterEmailRequestDTO emailRegisterRequest){
        try {
            Email email = emailService.registerEmail(emailRegisterRequest);
            return ResponseEntity.ok(email);
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
    public ResponseEntity<Email> getDeviceWithId(@PathVariable String id) {
        Email email = emailService.getEmailEntityById(id);
        return ResponseEntity.ok(email);
    }

}
