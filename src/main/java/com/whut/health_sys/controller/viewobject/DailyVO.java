package com.whut.health_sys.controller.viewobject;

public class DailyVO
{
    private Integer id;

    private Integer uid;

    private String recordtime;

    private String temperature;

    private String location;

    private String infection;

    private String familyinfection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime == null ? null : recordtime.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getInfection()
    {
        return infection;
    }

    public void setInfection(String infection)
    {
        this.infection = infection;
    }

    public String getFamilyinfection()
    {
        return familyinfection;
    }

    public void setFamilyinfection(String familyinfection)
    {
        this.familyinfection = familyinfection;
    }
}