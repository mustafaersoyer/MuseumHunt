package com.example.museumhunt.Model;

public class User {
    private String _gender;
    private String  _age;
    private String _location;


    public void setAge(String age)
    {
        this._age=age;
    }
    public void setLocation(String location)
    {
        this._location=location;
    }
    public void setGender(String gender)
    {
        this._gender=gender;
    }

    public String getGender()
    {
        return this._gender;
    }
    public String getAge()
    {
        return this._age;
    }
    public String getLocation()
    {
        return this._location;
    }

    public User(String name,String age,String location)
    {
        this._gender = name;
        this._age = age;
        this._location = location;
    }
    public User()
    {

    }
}
