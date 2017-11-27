package com.springboot.dropbox.controller;


import com.springboot.dropbox.model.File;
import com.springboot.dropbox.model.User;
import com.springboot.dropbox.repository.FileRepository;
import com.springboot.dropbox.repository.UserRepository;
import com.springboot.dropbox.service.FileService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/file")
public class FileController {

    private Logger LOG = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;



    private ResponseEntity<?> returnSuccessResponseEntity(JSONObject resSuccess) {
        HttpHeaders responseHeaders;


        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        resSuccess.put("status", 200);

        return new ResponseEntity(resSuccess.toString(), responseHeaders, HttpStatus.OK);
    }

    private ResponseEntity<?> returnErrorResponseEntity(JSONObject resError) {


        HttpHeaders responseHeaders;

        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        resError.put("status", 400);

        return new ResponseEntity(resError.toString(), responseHeaders, HttpStatus.BAD_REQUEST);

    }


    @PostMapping(path="/upload")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile multiPartFile, @RequestParam("emailID") String emailID,
                                        @RequestParam("fileName") String fileName) {

        File file = fileService.upload(multiPartFile,emailID, fileName);
        System.out.println("database processing for uploading completed");
        if(file!=null)
        {
            String currentWorkingDirectory = System.getProperty("user.dir")+System.getProperty("file.separator");
            JSONObject resJSON = new JSONObject(file);
            resJSON.put("filePath", currentWorkingDirectory+"src/main/resources/upload/"+fileName);
            return returnSuccessResponseEntity(resJSON);
        }
        else
        {
            JSONObject message = new JSONObject();
            message.put("message","File could not be uploaded");
            return returnErrorResponseEntity(message);

        }

    }

    /******************************************** File Share *******************************************/

    @PostMapping(path="/share")
    public ResponseEntity<?> fileShare(@RequestBody String req)
    {
        //System.out.println("entered in sharing"+req);
        JSONObject reqJSON = new JSONObject(req);
        String filePath=reqJSON.get("filePath").toString();
        String toEmailID=(reqJSON.get("toEmailID")).toString();
        File file = fileService.share(filePath, toEmailID);
        System.out.println("database processing for sharing completed");
        if(file!=null)
        {
            return returnSuccessResponseEntity(new JSONObject(file));
        }
        else
        {
            System.out.println("entered else");
            JSONObject message = new JSONObject();
            message.put("message","File could not be shared");
            return returnErrorResponseEntity(message);
        }

    }

    /******************************************** File Star *******************************************/

    @PostMapping(path="/star")
    public ResponseEntity<?> fileStar(@RequestBody String req)
    {
        //System.out.println("entered in sharing"+req);
        JSONObject reqJSON = new JSONObject(req);
        String filePath=reqJSON.get("filePath").toString();
        File file = fileService.star(filePath);
        System.out.println("database processing for starring completed");
        if(file!=null)
        {
            return returnSuccessResponseEntity(new JSONObject(file));
        }
        else
        {
            System.out.println("entered else");
            JSONObject message = new JSONObject();
            message.put("message","File could not be starred");
            return returnErrorResponseEntity(message);
        }

    }

    /******************************************** File Unstar *******************************************/

    @PostMapping(path="/unstar")
    public ResponseEntity<?> fileUnstar(@RequestBody String req)
    {
        //System.out.println("entered in sharing"+req);
        JSONObject reqJSON = new JSONObject(req);
        String filePath=reqJSON.get("filePath").toString();
        File file = fileService.unstar(filePath);
        System.out.println("database processing for unstarring completed");
        if(file!=null)
        {
            return returnSuccessResponseEntity(new JSONObject(file));
        }
        else
        {
            System.out.println("entered else");
            JSONObject message = new JSONObject();
            message.put("message","File could not be unstarred");
            return returnErrorResponseEntity(message);
        }

    }

    /******************************************** File Delete *******************************************/

    @PostMapping(path="/delete")
    public ResponseEntity<?> fileDelete(@RequestBody String req)
    {
        JSONObject reqJSON = new JSONObject(req);
        String filePath=reqJSON.get("filePath").toString();
        String emailID= reqJSON.get("emailID").toString();
        File file = fileService.delete(filePath,emailID);
        System.out.println("database processing for deleting completed");
        if(file!=null)
        {
            return returnSuccessResponseEntity(new JSONObject(file));
        }
        else
        {
            System.out.println("entered else");
            JSONObject message = new JSONObject();
            message.put("message","File could not be deleted");
            return returnErrorResponseEntity(message);
        }

    }
}
