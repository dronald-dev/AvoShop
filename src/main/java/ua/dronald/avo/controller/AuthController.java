package ua.dronald.avo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.dronald.avo.model.RegistrationModel;
import ua.dronald.avo.service.UserService;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        return new ModelAndView("registration")
                .addObject("user", new RegistrationModel());
    }

    @PostMapping("/registration")
    public ModelAndView createAccount(@ModelAttribute("user") @Valid RegistrationModel user, BindingResult result) {
        if(result.hasErrors())
            return new ModelAndView()
                .addObject("user", user)
                .addObject("result", result);


        if (!user.getPassword().equals(user.getMatchingPassword())) {
            result.addError(new ObjectError("User", "Password doesn't equal matching password"));
            return new ModelAndView()
                    .addObject("user", user)
                    .addObject("result", result);
        }

        if (userService.createNewAccount(user) == null) {
            result.addError(new ObjectError("User", "An account for that username/email already exists"));
            return new ModelAndView()
                    .addObject("user", user)
                    .addObject("result", result);
        }

        return new ModelAndView("redirect:/");
    }
}
