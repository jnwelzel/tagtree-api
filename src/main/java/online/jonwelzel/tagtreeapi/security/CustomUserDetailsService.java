package online.jonwelzel.tagtreeapi.security;

import online.jonwelzel.tagtreeapi.user.UserModel;
import online.jonwelzel.tagtreeapi.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUuid(UUID.fromString(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(user.getUuid().toString(), user.getPassword(), user.getRoles().stream()
                .map(roleModel -> new SimpleGrantedAuthority(roleModel.getName().name())).collect(Collectors.toList()));
    }
}
