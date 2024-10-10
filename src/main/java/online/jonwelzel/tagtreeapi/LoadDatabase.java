package online.jonwelzel.tagtreeapi;

import online.jonwelzel.tagtreeapi.role.ROLES;
import online.jonwelzel.tagtreeapi.role.RoleModel;
import online.jonwelzel.tagtreeapi.role.RoleRepository;
import online.jonwelzel.tagtreeapi.tag.TagModel;
import online.jonwelzel.tagtreeapi.user.UserModel;
import online.jonwelzel.tagtreeapi.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {

        return args -> {
            RoleModel adminRole = new RoleModel(ROLES.ADMIN);
            RoleModel userRole = new RoleModel(ROLES.USER);
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            roleRepository.findAll().forEach(role -> log.info("Preloaded " + role));

            UserModel user1 = new UserModel("bbaggins@shire.com", "bilbo", "Bilbo", "Baggins", Collections.emptySet(),
                    new SimpleDateFormat("yyyy-MM-dd").parse("1550-02-15"));
            TagModel tag1 = new TagModel("PSN", "og-baggins", user1);
            TagModel tag2 = new TagModel("Steam", "bilbaggins", user1);
            user1.setTags(Set.of(tag1, tag2));
            user1.setUuid(UUID.fromString("2239b026-f46a-4931-a9b2-21ab26894345"));
            user1.setRoles(Set.of(roleRepository.findByName(ROLES.ADMIN).get()));
            user1.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user1);

            UserModel user2 = new UserModel("fbaggins@shire.com","frodo", "Frodo", "Baggins", Collections.emptySet(),
                    new SimpleDateFormat("yyyy-MM-dd").parse("1610-02-15"));
            user2.setPassword(passwordEncoder.encode("user"));
            user2.setRoles(Set.of(roleRepository.findByName(ROLES.USER).get()));
            userRepository.save(user2);

            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
        };
    }
}
