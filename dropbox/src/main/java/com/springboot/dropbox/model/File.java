package com.springboot.dropbox.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class File {

    @Id
    private String filePath;

    private String FileName;

    private String FileType;


    private boolean isStared;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "creatorEmailId")
    //private User CreatedBy;

    private Date CreateTime;

    //@ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "updaterEmailId")
   // private User LastUpdatedBy;

    private Date LastUpdatedTime;


    @ManyToMany(mappedBy = "files")
    private List<User> users ;

    {
        users = new ArrayList<User>();
    }



    public String getFilePath() {
        return filePath;
    }

    public boolean isStared() {
        return isStared;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }


    public boolean getIsStared() {
        return isStared;
    }

    public void setIsStared(boolean isStared) {
        this.isStared = isStared;
    }

   /* public User getCreatedBy() {
        return CreatedBy;
    }


    public void setCreatedBy(User createdBy) {
        CreatedBy = createdBy;
    }
    */

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

//    public User getLastUpdatedBy() {
//        return LastUpdatedBy;
//    }
//
//    public void setLastUpdatedBy(User lastUpdatedBy) {
//        LastUpdatedBy = lastUpdatedBy;
//    }

    public Date getLastUpdatedTime() {
        return LastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        LastUpdatedTime = lastUpdatedTime;
    }

}
