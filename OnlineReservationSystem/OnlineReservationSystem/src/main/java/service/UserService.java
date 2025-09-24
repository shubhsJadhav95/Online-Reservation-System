package service;
import dao.UserRepository;
import helper.PasswordUtil;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User userRegister(String username, String email, String password) {
        // Check if user exists
        if (userRepository.findByUsername(username).isPresent() ||
                userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Username or Email already exists!");
        }

        // Encode password with BCrypt (jbcrypt)
        String encodedPassword = PasswordUtil.encode(password);
        User user = User.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .build();

        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return PasswordUtil.matches(password, user.getPassword());
        }
        return false;
    }
}

