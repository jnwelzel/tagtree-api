package online.jonwelzel.tagtreeapi.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUserName(String userName);
    Optional<UserModel> findByEmail(String email);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<UserModel> findByUuid(UUID uuid);
}
