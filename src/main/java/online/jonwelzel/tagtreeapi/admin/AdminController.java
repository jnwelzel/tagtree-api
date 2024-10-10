package online.jonwelzel.tagtreeapi.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<String> deleteUser() {
        return ResponseEntity.ok("Hello ADMIN");
    }
}
