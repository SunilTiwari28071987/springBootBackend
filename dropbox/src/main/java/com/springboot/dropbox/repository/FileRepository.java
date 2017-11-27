package com.springboot.dropbox.repository;


import com.springboot.dropbox.model.File;
import com.springboot.dropbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    File findByFilePath(String filePath);
}