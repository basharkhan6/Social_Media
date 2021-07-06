package com.example.socialmedia.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void secure() throws Exception {
        mockMvc.perform(get("/secure/page"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser
    @Test
    void secureWithMockUser() throws Exception {
        mockMvc.perform(get("/secure/page"))
                .andExpect(status().isOk());
    }

//    @WithUserDetails(value = "test@gmail.com", userDetailsServiceBeanName = "userDetailsServiceImpl")
//    @Test
//    void secureWithUser() throws Exception {
//        mockMvc.perform(get("/secure/page"))
//                .andExpect(status().isOk());
//    }
}