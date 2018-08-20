package com.build4d.web.platform.controller;

import com.build4d.web.platform.controller.base.BuilderHomeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {
    MockMvc mockMvc;

    @Before
    public void setupMock() {
        BuilderHomeController controller = new BuilderHomeController();
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void home() throws Exception {
        mockMvc.perform(get("/home.do"))
                .andExpect(view().name("Login"));
    }
}
