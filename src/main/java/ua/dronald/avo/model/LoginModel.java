package ua.dronald.avo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginModel {

    @NotEmpty
    private String login;

    @NotEmpty
    @Size(min = 8)
    private String password;
}
