package online.jonwelzel.tagtreeapi;

import online.jonwelzel.tagtreeapi.tag.Tag;
import online.jonwelzel.tagtreeapi.tag.TagRepository;
import online.jonwelzel.tagtreeapi.user.User;
import online.jonwelzel.tagtreeapi.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, TagRepository tagRepository) {

        return args -> {
            User user1 = new User("bbaggins@shire.com", "bilbo", "Bilbo", "Baggins", Collections.emptySet());
            Tag tag1 = new Tag("PSN", "og-baggins", user1);
            Tag tag2 = new Tag("Steam", "bilbaggins", user1);
            user1.setTags(Set.of(tag1, tag2));
            userRepository.save(user1);
            userRepository.save(new User("fbaggins@shire.com","frodo", "Frodo", "Baggins", Collections.emptySet()));

            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
        };
    }
}
