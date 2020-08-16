package com.whut.health_sys.controller.viewobject;

import java.util.ArrayList;
import java.util.List;

public class StepVO
{
    Integer uid;

    List<String> dates=new ArrayList<>();

    List<String> steps=new ArrayList<>();

    boolean todaysUpload;

    public List<String> getDates()
    {
        return dates;
    }

    public void setDates(List<String> dates)
    {
        this.dates = dates;
    }

    public List<String> getSteps()
    {
        return steps;
    }

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public void setSteps(List<String> steps)
    {
        this.steps = steps;
    }

    public boolean isTodaysUpload()
    {
        return todaysUpload;
    }

    public void setTodaysUpload(boolean todaysUpload)
    {
        this.todaysUpload = todaysUpload;
    }
}
