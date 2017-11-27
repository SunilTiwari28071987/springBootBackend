package com.springboot.dropbox.service;

import com.springboot.dropbox.model.File;
import com.springboot.dropbox.model.User;
import com.springboot.dropbox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User signUp(User user){

        if(userRepository.findByEmailID(user.getEmailID())!=null)
            return null;

        return userRepository.save(user);


    }

    public User signIn(User user){

        User dbUser = userRepository.findByEmailID(user.getEmailID());
        if(dbUser==null)
            return null;
        if(!dbUser.getPassword().equals(user.getPassword()))
            return null;

        return dbUser;

    }
}
