package co.shop.api.interfaces.services;

import co.shop.api.dtos.userDto.LoginUserDto;
import co.shop.api.dtos.userDto.RegisterUserDto;
import co.shop.api.dtos.userDto.UserDto;

public interface IUserService {
    UserDto getUserDetails(Long id);
    Void RegisterUser(RegisterUserDto registerUserDto);
    Void loginUser(LoginUserDto loginUserDto);
}
