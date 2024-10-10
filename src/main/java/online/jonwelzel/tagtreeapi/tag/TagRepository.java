package online.jonwelzel.tagtreeapi.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagModel, Long> {
    Optional<TagModel> findByIdAndUserUuid(Long id, UUID userId);
    List<TagModel> findAllByUserUuid(UUID userId);
    void deleteByIdAndUserUuid(Long id, UUID userId);
}
