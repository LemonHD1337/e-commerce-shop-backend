package co.shop.api.controllers;

import co.shop.api.dtos.userDto.LoginUserDto;
import co.shop.api.dtos.userDto.RegisterUserDto;
import co.shop.api.dtos.userDto.UserDto;
import co.shop.api.interfaces.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(_userService.getUserDetails(id));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(_userService.RegisterUser(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(_userService.loginUser(loginUserDto));
    }
}
