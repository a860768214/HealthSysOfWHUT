package com.whut.health_sys.controller.viewobject;

public class ReservationVO
{
    private Integer rid;

    private Integer uid;

    private Integer cid;

    private String time;

    private String status;

    private String msg;

    private String dept;

    public Integer getRid()
    {
        return rid;
    }

    public void setRid(Integer rid)
    {
        this.rid = rid;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public Integer getCid()
    {
        return cid;
    }

    public void setCid(Integer cid)
    {
        this.cid = cid;
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

    public String getDept()
    {
        return dept;
    }

    public void setDept(String dept)
    {
        this.dept = dept;
    }
}
