package online.jonwelzel.tagtreeapi.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    public UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("{id}")
    public EntityModel<UserModel> one(@PathVariable UUID id) {
        UserModel user = repository.findByUuid(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        return assembler.toModel(user);
    }

    @GetMapping("me")
    public EntityModel<UserModel> me(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        UserModel user = repository.findByUuid(UUID.fromString(username)).orElseThrow(() -> new UserNotFoundException(username));

        return assembler.toModel(user);
    }

    @GetMapping()
    public CollectionModel<EntityModel<UserModel>> all() {
        List<EntityModel<UserModel>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }
}
