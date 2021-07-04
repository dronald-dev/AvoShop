package ua.dronald.avo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
