package com.whut.health_sys.dataobject;

public class UserDO {
    private Integer uid;

    private String name;

    private Integer gender;

    private String qq;

    private String phone;

    private String schmail;

    private String cretime;

    private Integer status;

    private Integer age;

    private Double height;

    private Double weight;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSchmail() {
        return schmail;
    }

    public void setSchmail(String schmail) {
        this.schmail = schmail == null ? null : schmail.trim();
    }

    public String getCretime() {
        return cretime;
    }

    public void setCretime(String cretime) {
        this.cretime = cretime == null ? null : cretime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}