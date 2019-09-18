package com.example.museumhunt.UI.user_info;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.museumhunt.Model.User;



public class UserInfoViewModel extends BaseObservable {
    User user = new User();

    public UserInfoViewModel() { }

    public void setAge(String age) {
        user.setAge(age);
        //notifyPropertyChanged(com.example.museumhunt.BR.userAge);
    }
    @Bindable
    public String getUserAge() {
        return user.getAge();
    }


    public void setLocation(String location) {
        user.setLocation(location);
       // notifyPropertyChanged(com.example.museumhunt.BR.location);
    }

    @Bindable
    public String getLocation() {
        return user.getLocation();
    }

    public void setGender(String location) {
        user.setGender(location);
    }

    @Bindable
    public String getGender() {
        return user.getGender();
    }


}
