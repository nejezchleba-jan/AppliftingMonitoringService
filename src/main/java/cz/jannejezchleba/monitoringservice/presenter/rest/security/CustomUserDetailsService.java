package cz.jannejezchleba.monitoringservice.presenter.rest.security;

import cz.jannejezchleba.monitoringservice.domain.entities.user.User;
import cz.jannejezchleba.monitoringservice.domain.usecases.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        User customerData = userRepository
                .getUserByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserPrincipal.from(customerData);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User customerData = userRepository
                .getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));

        return UserPrincipal.from(customerData);
    }
}