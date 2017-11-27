package com.springboot.dropbox.service;

import com.springboot.dropbox.model.File;
import com.springboot.dropbox.model.User;
import com.springboot.dropbox.repository.FileRepository;
import com.springboot.dropbox.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageService storageService;


    public File upload(MultipartFile file, String emailID, String fileName){

         try {
             storageService.store(file);
             String currentWorkingDirectory = System.getProperty("user.dir")+System.getProperty("file.separator");
             File newFile = new File();
             newFile.setFileName(fileName);
             newFile.setCreateTime(new Date());
             newFile.setFilePath(currentWorkingDirectory+"src/main/resources/upload/"+fileName);
             newFile.setIsStared(false);
             newFile.setLastUpdatedTime(new Date());
             newFile.setFileType("file");


             User user = userRepository.findByEmailID(emailID);
             System.out.println("found the user for file");
             //newFile.getUsers().add(user);
             System.out.println("user added for file");
             File retFile = fileRepository.save(newFile);
             System.out.println("file saved");
             user.getFiles().add(newFile);
             System.out.println("file added for user");
             userRepository.save(user);
             System.out.println("saving user");
             return retFile;
         }
         catch(Exception e)
         {
             System.out.println(e.toString());
            return null;
         }
    }


    public File share(String filePath, String toEmailID) {

        User user = userRepository.findByEmailID(toEmailID);
        File fileShared = fileRepository.findByFilePath(filePath);
        System.out.println("found the user for file");
        //newFile.getUsers().add(user);
        System.out.println("user added for file");
        user.getFiles().add(fileShared);
        userRepository.save(user);
        return fileShared;
    }

    public File star(String filePath) {

        File fileStarred = fileRepository.findByFilePath(filePath);
        fileStarred.setIsStared(true);
        fileRepository.save(fileStarred);
        return fileStarred;
    }
}
