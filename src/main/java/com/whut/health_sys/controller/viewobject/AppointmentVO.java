package com.whut.health_sys.controller.viewobject;

public class AppointmentVO
{
    private Integer aid;

    private Integer uid;

    private Integer did;

    private String time;

    private String status;

    private String msg;

    public Integer getAid()
    {
        return aid;
    }

    public void setAid(Integer aid)
    {
        this.aid = aid;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public Integer getDid()
    {
        return did;
    }

    public void setDid(Integer did)
    {
        this.did = did;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}