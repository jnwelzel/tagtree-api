package online.jonwelzel.tagtreeapi.tag;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class TagController {
    private final TagRepository tagRepository;
    private final TagModelAssembler tagModelAssembler;

    public TagController(TagRepository tagRepository, TagModelAssembler tagModelAssembler) {
        this.tagRepository = tagRepository;
        this.tagModelAssembler = tagModelAssembler;
    }

    @GetMapping("users/{userId}/tags/{id}")
    public EntityModel<TagModel> one(@PathVariable long userId, @PathVariable long id) {
        TagModel tag = tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(userId, id));

        return tagModelAssembler.toModel(tag);
    }

    @GetMapping("users/{userId}/tags")
    public CollectionModel<EntityModel<TagModel>> all(@PathVariable long userId) {
        List<EntityModel<TagModel>> tags = tagRepository.findTagsByUserId(userId).stream().map(tagModelAssembler::toModel).toList();

        return CollectionModel.of(tags, linkTo(methodOn(TagController.class).all(userId)).withSelfRel());
    }
}
