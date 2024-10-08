package online.jonwelzel.tagtreeapi.tag;

import online.jonwelzel.tagtreeapi.user.UserModel;
import online.jonwelzel.tagtreeapi.user.UserNotFoundException;
import online.jonwelzel.tagtreeapi.user.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagRepository tagRepository;
    private final TagModelAssembler tagModelAssembler;
    private final UserRepository userRepository;

    public TagController(TagRepository tagRepository, TagModelAssembler tagModelAssembler, UserRepository userRepository) {
        this.tagRepository = tagRepository;
        this.tagModelAssembler = tagModelAssembler;
        this.userRepository = userRepository;
    }

    @GetMapping("{id}")
    public EntityModel<TagModel> one(@PathVariable long id) {
        TagModel tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));

        return tagModelAssembler.toModel(tag);
    }

    @GetMapping
    public CollectionModel<EntityModel<TagModel>> all() {
        List<EntityModel<TagModel>> tags = tagRepository.findAll().stream().map(tagModelAssembler::toModel).toList();

        return CollectionModel.of(tags, linkTo(methodOn(TagController.class).all()).withSelfRel());
    }

    @PostMapping()
    public CollectionModel<EntityModel<TagModel>> add(@RequestBody TagModel tag, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        UserModel user = userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException(username));
        TagModel newTag = new TagModel(tag.getName(), tag.getDescription(), user);
        user.getTags().add(newTag);
        userRepository.save(user);

        return CollectionModel.of(user.getTags().stream().map(tagModelAssembler::toModel).toList(),
                linkTo(TagController.class).withSelfRel());
    }
}
