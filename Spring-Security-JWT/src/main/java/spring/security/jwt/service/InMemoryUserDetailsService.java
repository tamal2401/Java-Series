package spring.security.jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private static final List<User> users = new ArrayList<>();

    public static final String pwd = "$2a$10$XMyWD9X08Hd8MjWNgm.StuytsQqm7qZP1Kz6hvGoGltE.46Pbbw1m";

    static{
        users.add(new User("tamal", pwd, new ArrayList<>()));
        users.add(new User("pallavi", pwd, new ArrayList<>()));
        users.add(new User("jhilik", pwd, new ArrayList<>()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> currentUser = users.stream()
                .filter(each -> each.getUsername().equals(username))
                .findFirst();
        currentUser.orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found with username: " + username);
        });
        return currentUser.get();
    }
}
