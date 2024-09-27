package co.shop.api.dtos.userDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {


    @NotBlank(message = "Imię nie może być puste!")
    @Size(max = 50, min = 3, message = "Imię musi mieć od 3 do 50 znaków")
    private String firstName;


    @NotBlank(message = "Nazwisko nie może być puste!")
    @Size(max = 70, min = 3, message = "Imię musi mieć od 3 do 70 znaków")
    private String lastName;

    @NotBlank(message = "Adres email nie może być pusty!")
    @Email(message = "Email musi być w poprawnym formacie np. testowy@gmail.com")
    @Size(max = 50, min = 3, message = "Email musi mieć od 3 do 50 znaków")
    private String email;

    @NotBlank(message = "Hasło nie może być puste!")
    @Size(min = 8, max = 50, message = "Hasło musi mieć od 8 do 50 znaków")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,50}$",
    message = "Hasło musi zawierać chociaż jedną liczbę, jedną małą literę, jedną dużą literę, jeden znak specjalny oraz nie może zawierać spacji")
    private String password;
}
