package online.jonwelzel.tagtreeapi.admin;

import online.jonwelzel.tagtreeapi.role.ROLES;
import online.jonwelzel.tagtreeapi.role.RoleRepository;
import online.jonwelzel.tagtreeapi.user.UserModel;
import online.jonwelzel.tagtreeapi.user.UserNotFoundException;
import online.jonwelzel.tagtreeapi.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("Deleted user " + id);
    }

    @PutMapping("make-admin/{id}")
    public ResponseEntity<UserModel> makeUserAdmin(@PathVariable Long id) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.getRoles().clear();
        user.getRoles().add(roleRepository.findByName(ROLES.ADMIN).get());
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
