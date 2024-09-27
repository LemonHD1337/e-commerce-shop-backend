package co.shop.api.translator;

import co.shop.api.dtos.userDto.RegisterUserDto;
import co.shop.api.dtos.userDto.UserDto;
import co.shop.api.entities.User;
import co.shop.api.interfaces.mappers.IUserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IUserMapper {


    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public User fromRegisterUserDtoToEntity(RegisterUserDto registerUserDto) {
        var user = new User();

        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(registerUserDto.getPassword());
        return user;
    }
}
