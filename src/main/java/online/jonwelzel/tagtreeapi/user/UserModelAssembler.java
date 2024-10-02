package online.jonwelzel.tagtreeapi.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserModel, EntityModel<UserModel>> {
    @Override
    public EntityModel<UserModel> toModel(UserModel user) {
        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                linkTo(UserController.class).slash(user.getId()).slash("tags").withRel("tags"),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}
