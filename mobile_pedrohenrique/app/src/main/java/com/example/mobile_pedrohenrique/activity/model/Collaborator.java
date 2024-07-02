package com.example.mobile_pedrohenrique.activity.model;

import java.io.Serializable;
import java.util.Date;

public class Collaborator implements Serializable {

    private long id = 0;
    private int cracha = 0;
    private Date startService = new Date();
    private Date endService = null;

    public Collaborator() {
    }

    public Collaborator(int cracha, Date startService) {
        this.cracha = cracha;
        this.startService = startService;
    }

    public int getCracha() {
        return cracha;
    }

    public void setCracha(int cracha) {
        this.cracha = cracha;
    }

    public Date getStartService() {
        return startService;
    }

    public void setStartService(Date startService) {
        this.startService = startService;
    }

    public Date getEndService() {
        return endService;
    }

    public void setEndService(Date endService) {
        this.endService = endService;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
