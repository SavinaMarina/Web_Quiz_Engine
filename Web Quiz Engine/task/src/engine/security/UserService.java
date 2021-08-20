package engine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return new UserDetailsImpl(userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username)));
   }

    public void save(User user) {
        String email = user.getEmail();
        if (findByEmail(email).isPresent())
            throw new UserAlreadyExistsException(email);
        user.setPassword(passwordEncoderConfig.getEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public User getCurrentUser() {
        return findByEmail(authenticationFacade.getAuthentication().getName())
                .orElseThrow();
    }
}
