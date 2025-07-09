package com.sa.imoveis.controller;

import com.sa.imoveis.dto.UserDTO;
import com.sa.imoveis.model.User;
import com.sa.imoveis.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
public class UserController {
    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User searchById(@PathVariable Long id) {
        return userService.searchById(id);
    }

    @GetMapping("/login")
    public User logIn(@RequestParam String email, @RequestParam String password) {
        return userService.logIn(email, password);
    }

    @PostMapping
    public User create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    public User edit(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return userService.edit(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
