package com.Entity;

import java.util.Date;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-07 14:28
 */
public class filemetas2 {
    private Integer id;
    private String filesize;
    private String filetype;
    private String fileoldname;
    private String filecreatetime;
    private String filesavepath;

    public filemetas2() {
    }

    public filemetas2(Integer id, String filesize, String filetype, String fileoldname, String filecreatetime, String filesavepath) {
        this.id = id;
        this.filesize = filesize;
        this.filetype = filetype;
        this.fileoldname = fileoldname;
        this.filecreatetime = filecreatetime;
        this.filesavepath = filesavepath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFileoldname() {
        return fileoldname;
    }

    public void setFileoldname(String fileoldname) {
        this.fileoldname = fileoldname;
    }

    public String getFilecreatetime() {
        return filecreatetime;
    }

    public void setFilecreatetime(String filecreatetime) {
        this.filecreatetime = filecreatetime;
    }

    public String getFilesavepath() {
        return filesavepath;
    }

    public void setFilesavepath(String filesavepath) {
        this.filesavepath = filesavepath;
    }

    @Override
    public String toString() {
        return "filemetas2{" +
                "id=" + id +
                ", filesize='" + filesize + '\'' +
                ", filetype='" + filetype + '\'' +
                ", fileoldname='" + fileoldname + '\'' +
                ", filecreatetime='" + filecreatetime + '\'' +
                ", filesavepath='" + filesavepath + '\'' +
                '}';
    }
}
