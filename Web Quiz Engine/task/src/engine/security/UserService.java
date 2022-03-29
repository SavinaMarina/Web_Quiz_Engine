package engine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final IAuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(IAuthenticationFacade authenticationFacade, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationFacade = authenticationFacade;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return new UserDetailsImpl(userRepository.findByEmail(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username)));
   }

    public void save(User user) {
        String email = user.getEmail();
        if (findByEmail(email).isPresent())
            throw new UserAlreadyExistsException(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
