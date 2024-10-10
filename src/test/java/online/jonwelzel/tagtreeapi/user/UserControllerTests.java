package online.jonwelzel.tagtreeapi.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    Jwt jwt = Jwt.withTokenValue("token")
            .header("alg", "RS256")
            .claim("scope", "ADMIN")
            .claim("sub", "2239b026-f46a-4931-a9b2-21ab26894345").build();

    @Test
    void one() throws Exception {
        mockMvc.perform(get("/api/v1/users/1").with(jwt().jwt(jwt)))
                .andExpect(status().isOk());
    }

    @Test
    void all() throws Exception {
        mockMvc.perform(get("/api/v1/users").with(jwt().jwt(jwt)))
                .andExpect(status().isOk());
    }
}
