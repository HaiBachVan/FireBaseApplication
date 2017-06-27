package app.demo.firebase.model;

public class Student {

    private String name;
    private String address;
    private int year;

    public Student() {

    }

    public Student(String name, String address, int year) {
        this.name = name;
        this.address = address;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }
}
