package co.shop.api.dtos.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {

    @NotBlank(message = "Email nie może być pusty!")
    @Email(message = "Email musi być w poprawnym formacie np. testowy@gmail.com")
    private String email;

    @NotBlank(message = "Hasło nie może być puste!")
    @Size(min = 8, max = 50, message = "Hasło musi mieć od 8 do 50 znaków")
    private String password;
}
