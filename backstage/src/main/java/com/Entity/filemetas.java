package com.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 9:24
 */
public class filemetas implements Serializable {
        private Integer id;
        private String filesize;
        private String filetype;
        private String fileoldname;
        private Date filecreatetime;
        private String filesavepath;

    public filemetas() {
    }

    public filemetas(String filesize, String filetype, String fileoldname, Date filecreatetime, String filesavepath) {

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

    public Date getFilecreatetime() {
        return filecreatetime;
    }

    public void setFilecreatetime(Date filecreatetime) {
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
        return "filemetas{" +
                "id=" + id +
                ", filesize='" + filesize + '\'' +
                ", filetype='" + filetype + '\'' +
                ", fileoldname='" + fileoldname + '\'' +
                ", filecreatetime=" + filecreatetime +
                ", filesavepath='" + filesavepath + '\'' +
                '}';
    }
}
