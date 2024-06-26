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

// JUnit 5's ExtendWith annotation to enable Spring support
@ExtendWith(SpringExtension.class)
// SpringBootTest annotation to indicate that this is a Spring Boot test
@SpringBootTest
// AutoConfigureMockMvc annotation to auto-configure the MockMvc instance
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Test method for checking the login page
    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")) // Perform GET request to "/login"
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP status code 200 (OK)
                .andExpect(MockMvcResultMatchers.view().name("auth/signin")); // Expect the view name to be "auth/signin"
    }

    // Test method for checking the register page
    @Test
    public void testRegisterPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register")) // Perform GET request to "/register"
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP status code 200 (OK)
                .andExpect(MockMvcResultMatchers.view().name("auth/signup")); // Expect the view name to be "auth/signup"
    }
}
