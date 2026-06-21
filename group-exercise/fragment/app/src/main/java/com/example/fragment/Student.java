package com.example.fragment;

public class Student {
    public String id;
    public String name;
    public String className;
    public double gpa;
    public String avatarUrl;

    public Student(String id, String name, String className, double gpa, String avatarUrl) {
        this.id = id; this.name = name; this.className = className; this.gpa = gpa; this.avatarUrl = avatarUrl;
    }
}
