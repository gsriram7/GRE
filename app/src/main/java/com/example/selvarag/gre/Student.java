package com.example.selvarag.gre;

/**
 * Created by selvarag on 26-12-2014.
 */
public class Student {

    private int id;
    private String name;
    private int age;
    private String syn;
    private String expl;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setExpl(String expl) {
        this.expl = expl;
    }

    public String getExpl() {
        return expl;
    }

    public void setSyn(String syn) {
        this.syn = syn;
    }

    public String getSyn() {
        return syn;
    }

    @Override
    public String toString() {
        return name+" - "+syn;
    }
}