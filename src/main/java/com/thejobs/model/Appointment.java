package com.thejobs.model;

public class Appointment {
    private int apnmId;
    private int conId;
    private int jbsId;
    private int avbId;
    private String apnmDesc;
    private String apnmCountry;
    private String apnmJob;
    private String avbDate;
    private String avbTime;

    public Appointment(int apnmId, int conId, int jbsId, int avbId, String apnmDesc, String apnmCountry, String apnmJob, String avbDate, String avbTime) {
        this.apnmId = apnmId;
        this.conId = conId;
        this.jbsId = jbsId;
        this.avbId = avbId;
        this.apnmDesc = apnmDesc;
        this.apnmCountry = apnmCountry;
        this.apnmJob = apnmJob;
        this.avbDate = avbDate;
        this.avbTime = avbTime;
    }

    public int getApnmId() {
        return apnmId;
    }

    public void setApnmId(int apnmId) {
        this.apnmId = apnmId;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getJbsId() {
        return jbsId;
    }

    public void setJbsId(int jbsId) {
        this.jbsId = jbsId;
    }

    public int getAvbId() {
        return avbId;
    }

    public void setAvbId(int avbId) {
        this.avbId = avbId;
    }

    public String getApnmDesc() {
        return apnmDesc;
    }

    public void setApnmDesc(String apnmDesc) {
        this.apnmDesc = apnmDesc;
    }

    public String getApnmCountry() {
        return apnmCountry;
    }

    public void setApnmCountry(String apnmCountry) {
        this.apnmCountry = apnmCountry;
    }

    public String getApnmJob() {
        return apnmJob;
    }

    public void setApnmJob(String apnmJob) {
        this.apnmJob = apnmJob;
    }
    public String getAvbDate() {
        return avbDate;
    }

    public void setAvbDate(String avbDate) {
        this.avbDate = avbDate;
    }
    public String getAvbTime() {
        return avbTime;
    }

    public void setAvbTime(String avbTime) {
        this.avbTime = avbTime;
    }
}
