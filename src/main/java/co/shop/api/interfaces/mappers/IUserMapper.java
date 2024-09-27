package co.shop.api.interfaces.mappers;

import co.shop.api.dtos.userDto.RegisterUserDto;
import co.shop.api.dtos.userDto.UserDto;
import co.shop.api.entities.User;

public interface IUserMapper {
    UserDto toUserDto(User user);
    User fromRegisterUserDtoToEntity(RegisterUserDto registerUserDto);
}
