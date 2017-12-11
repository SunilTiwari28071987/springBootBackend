package com.springboot.dropbox.controller;


import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/session")
public class SessionController {


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


    /******************************************** User Session *******************************************/

    @PostMapping(value = "/checksession")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> checkSession(HttpSession session) {

        System.out.println(session.getAttribute("emailID"));
        System.out.println(session.getAttribute("isLoggedIn"));

        String emailID = session.getAttribute("emailID").toString();
        boolean isLoggedIn = (boolean)session.getAttribute("isLoggedIn");
        if(!isLoggedIn)
        {
            JSONObject message = new JSONObject();
            message.put("message","User is not loggedIn");
            return returnErrorResponseEntity(message);
        }
        JSONObject resJSON = new JSONObject();
        resJSON.put("message","User is loggedIn");
        return returnSuccessResponseEntity(resJSON);
    }


}
