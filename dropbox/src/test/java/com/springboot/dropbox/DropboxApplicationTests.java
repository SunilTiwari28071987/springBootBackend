package com.springboot.dropbox;

import com.springboot.dropbox.controller.UserController;
import com.springboot.dropbox.model.File;
import com.springboot.dropbox.model.User;
import com.springboot.dropbox.repository.FileRepository;
import com.springboot.dropbox.repository.UserRepository;
import com.springboot.dropbox.service.UserService;
import javafx.application.Application;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DropboxApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    private User expectedUser;
    private File expectedFile;


    @Before
    public void setup() throws JSONException {

        expectedUser = userRepository.findByEmailID("Sunil28071987@gmail.com");
        expectedFile = fileRepository.findByFilePath("./resources/upload/CASHNet.pdf");

    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserFirstName() {

        assertEquals(expectedUser.getFirstName(), "Sunil");
    }

    @Test
    public void testUserLastName() {

        assertEquals(expectedUser.getLastName(), "Tiwari");
    }

    @Test
    public void testUserEmail() {

        assertEquals(expectedUser.getEmailID(), "Sunil28071987@gmail.com");
    }

    @Test
    public void testUserPassword() {

        assertEquals(expectedUser.getPassword(), "Sunil@28");
    }

    @Test
    public void testFilePath() {

        assertEquals(expectedFile.getFilePath(), "./resources/upload/CASHNet.pdf");
    }

    @Test
    public void testFileName() {

        assertEquals(expectedFile.getFileName(), "CASHNet.pdf");
    }

    @Test
    public void testFileType() {

        assertEquals(expectedFile.getFileType(), "file");
    }

    @Test
    public void testisIsStared() {

        assertEquals(expectedFile.isIsStared(), true);
    }

    @Test
    public void testisIsShared() {

        assertEquals(expectedFile.isIsShared(), true);
    }


}
