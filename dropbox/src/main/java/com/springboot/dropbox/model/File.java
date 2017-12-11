package com.springboot.dropbox.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class File {

    @Id
    private String filePath;

    private String fileName;

    private String fileType;

    private boolean isIsStared;

    private boolean isIsShared;

    private Date createTime;

    private Date lastModified;

    @ManyToMany(mappedBy = "files")
    private List<User> users;

    {
        users = new ArrayList<User>();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isIsStared() {
        return isIsStared;
    }

    public void setIsStared(boolean isStared) {
        isIsStared = isStared;
    }

    public boolean isIsShared() {
        return isIsShared;
    }

    public void setIsShared(boolean isShared) {
        isIsShared = isShared;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastModified() {
        return lastModified.getTime();
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
