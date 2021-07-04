package ua.dronald.avo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.dronald.avo.entity.Role;
import ua.dronald.avo.entity.User;
import ua.dronald.avo.model.RegistrationModel;
import ua.dronald.avo.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewAccount(RegistrationModel registrationModel) {

        if(emailExists(registrationModel.getEmail()) || phoneExists(registrationModel.getPhone()))
            return null;

        return repository.save(User.builder()
                .firstName(registrationModel.getFirstName())
                .lastName(registrationModel.getLastName())
                .phone(registrationModel.getPhone())
                .email(registrationModel.getEmail())
                .password(passwordEncoder.encode(registrationModel.getPassword()))
                .roles(Collections.singleton(Role.USER))
                .build()
        );
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email) != null;
    }

    private boolean phoneExists(String phone) {
        return repository.findByPhone(phone) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(repository.findByEmail(username) == null)
            throw new UsernameNotFoundException("UsernameNotFoundException");
        return repository.findByEmail(username);
    }
}
