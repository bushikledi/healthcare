package com.healthcare.controller;

import com.healthcare.model.User;
import com.healthcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        userService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

}
