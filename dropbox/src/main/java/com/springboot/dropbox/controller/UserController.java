package com.springboot.dropbox.controller;

import com.springboot.dropbox.model.User;
import com.springboot.dropbox.repository.UserRepository;
import com.springboot.dropbox.service.StorageService;
import com.springboot.dropbox.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {



    private Logger LOG = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;


    private ResponseEntity<?> returnSuccessResponseEntity(JSONObject resSuccess) {
        HttpHeaders responseHeaders;


        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        resSuccess.put("status",200);

        return new ResponseEntity(resSuccess.toString(), responseHeaders, HttpStatus.OK);
    }

    private ResponseEntity<?> returnErrorResponseEntity(JSONObject resError)
    {


        HttpHeaders responseHeaders;

        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        resError.put("status",400);

        return new ResponseEntity(resError.toString(), responseHeaders, HttpStatus.BAD_REQUEST);

    }

//    @RequestMapping(path="/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
//    public User add (@RequestBody User user){
//        return userRepo.save(user);
//    }

    /******************************************** User SignUp *******************************************/

    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        User newUser = userService.signUp(user);
        if(newUser==null)
        {
            JSONObject message = new JSONObject();
            message.put("message","User already exists");
            return returnErrorResponseEntity(message);
        }

        JSONObject resJSON = new JSONObject(newUser);
        return returnSuccessResponseEntity(resJSON);

    }


    /******************************************** User SignIn *******************************************/

    @PostMapping(path="/signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody User user,HttpSession session)
    {
        User existingUser = userService.signIn(user);
        if(existingUser==null)
        {
            JSONObject message = new JSONObject();
            message.put("message","User does not exists");
            return returnErrorResponseEntity(message);
        }
        session.setAttribute("emailID",user.getEmailID());
        session.setAttribute("isLoggedIn",true);
        JSONObject resJSON = new JSONObject(existingUser);
        return returnSuccessResponseEntity(resJSON);

    }



    /******************************************** User SignOut *******************************************/

    @PostMapping(value = "/signout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> signout(HttpSession session) {
        System.out.println(session.getAttribute("emailID"));
        session.invalidate();
        JSONObject resJson = new JSONObject();
        resJson.put("message","User successfully sign out");
        return returnSuccessResponseEntity(resJson);
    }



}

