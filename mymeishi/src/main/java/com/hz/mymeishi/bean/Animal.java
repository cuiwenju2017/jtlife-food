package com.hz.mymeishi.bean;

public class Animal {
    private String aName;
    private String aSpeak;
    private int aIcon;

    public Animal(){
    }

    public Animal(String aName, String aSpeak, int aIcon){
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;
    }

    public String getaName() {
        return aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public int getaIcon() {
        return aIcon;
    }
}
