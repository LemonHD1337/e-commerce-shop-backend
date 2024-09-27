package co.shop.api.services;

import co.shop.api.dtos.userDto.LoginUserDto;
import co.shop.api.dtos.userDto.RegisterUserDto;
import co.shop.api.dtos.userDto.UserDto;
import co.shop.api.exception.BadRequestException;
import co.shop.api.exception.ResourceNotFoundException;
import co.shop.api.interfaces.mappers.IUserMapper;
import co.shop.api.interfaces.services.IUserService;
import co.shop.api.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository _userRepository;
    private final IUserMapper _userMapper;
    private final PasswordEncoder _passwordEncoder;

    public UserService(UserRepository userRepository, IUserMapper userMapper, PasswordEncoder passwordEncoder) {
        this._userRepository = userRepository;
        this._userMapper = userMapper;
        this._passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserDetails(Long id) {
        return _userRepository
                .findById(id)
                .map(_userMapper::toUserDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with id: " + id)
                );
    }

    @Override
    public Void RegisterUser(RegisterUserDto registerUserDto) {
        registerUserDto.setPassword(_passwordEncoder.encode(registerUserDto.getPassword()));
        _userRepository.save(_userMapper.fromRegisterUserDtoToEntity(registerUserDto));
        return null;
    }

    @Override
    public UserDto loginUser(LoginUserDto loginUserDto) {
        var user = _userRepository.findByEmail(loginUserDto.getEmail());

        if(user == null) {
            throw new ResourceNotFoundException("User not found with email: " + loginUserDto.getEmail());
        }

        if(!_passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong password");
        }


        return _userMapper.toUserDto(user);
    }
}
