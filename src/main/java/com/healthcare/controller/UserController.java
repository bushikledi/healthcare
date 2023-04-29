package com.healthcare.controller;

import com.healthcare.model.records.UserRequest;
import com.healthcare.model.records.UserResponse;
import com.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        userService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UserRequest user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

}