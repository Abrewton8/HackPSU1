package com.barelypassing.hackpsu;

/**
 * Created by Shannon on 11/5/2017.
 */

public class Tutor {
    private String name;
    private String digits;
    private String classCode;
    private String major;

    public Tutor() {

    }

    public Tutor(String name, String digits, String classCode, String major) {
        this.name = name;
        this.digits = digits;
        this.classCode = classCode;
        this.major = major;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDigits(String digits){
        this.digits = digits;
    }

    public void setClassCode(String classCode){
        this.classCode = classCode;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public String getName(){
        return name;
    }

    public String getDigits(){
        return digits;
    }

    public String getClassCode() {
        return classCode;
    }
    public String getMajor() {
        return major;
    }
}
