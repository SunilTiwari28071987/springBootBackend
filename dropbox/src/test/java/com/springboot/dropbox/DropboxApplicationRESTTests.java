package com.springboot.dropbox;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.dropbox.controller.UserController;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class DropboxApplicationRESTTests extends DropboxApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    JSONObject req;

    @Before
    public void setup() throws JSONException {

        req = new JSONObject();
        req.put("emailID","Sunil28071987@gmail.com");
        req.put("password","Sunil@28");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //String json = mapper.writeValueAsString(someClass);
    }

    @Test
    public void testSignIn() throws Exception {
        mockMvc.perform(post("/user/signin").contentType(MediaType.APPLICATION_JSON)
                .content(req.toString())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.firstName").value("Sunil")).andExpect(jsonPath("$.lastName").value("Tiwari"))
                .andExpect(jsonPath("$.emailID").value("Sunil28071987@gmail.com")).andExpect(jsonPath("$.password").value("Sunil@28"));

    }

}