package online.jonwelzel.tagtreeapi.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagModel, Long> {
    List<TagModel> findTagsByUserId(Long userId);
}
