package myy803.springboot.OnlineBookStore.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserProfilePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/saveProfile"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) // Expecting redirection
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/profile")); // Example of expected redirection URL
    }

}

