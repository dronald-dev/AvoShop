package ua.dronald.avo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationModel {

    @Size(min = 2, max = 12)
    @NotEmpty
    private String firstName;

    @Size(min = 2, max = 12)
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 13, max = 13)
    private String phone;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    @Size(min = 8)
    private String matchingPassword;

}
