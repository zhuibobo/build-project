package com.build4d.web.platform.controller.builder;

import com.build4d.web.beanconfig.sys.RootConfig;
import com.build4d.web.beanconfig.sys.WebConfig;
import com.build4d.web.general.NormalMenuUtility;
import com.build4d.web.general.model.NormalMenuVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = RootConfig.class),
        @ContextConfiguration(name = "child", classes = WebConfig.class)})
public class FrameControllerTest {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private NormalMenuUtility builderMenuUtility;

    @Before
    public void setupMock() {
        mockMvc = webAppContextSetup(context).build();
    }


    @Test
    public void getMenuJson() throws Exception {
        /*System.out.println("------------------");
        System.out.println(context.getServletContext().getRealPath(""));
        System.out.println("------------------");*/
        List<NormalMenuVo> menuVos=builderMenuUtility.getMenus("/Views/Builder/Config/MenusConfig.xml");
        ObjectMapper objectMapper = new ObjectMapper();
        String personJson=objectMapper.writeValueAsString(menuVos);
        System.out.println(personJson);
        mockMvc.perform(get("/builder/frame/get_menu_json"))
                .andExpect(content().string(personJson));
    }
}