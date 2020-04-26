package by.it.academy.elearning.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String phone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 5, max = 80)
    private String password;
    @NonNull
    private Integer role;


}
