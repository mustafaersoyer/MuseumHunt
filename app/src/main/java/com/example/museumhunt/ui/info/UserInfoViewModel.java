package com.example.museumhunt.ui.info;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.museumhunt.Model.User;



public class UserInfoViewModel extends BaseObservable {
    User user = new User();
    private MutableLiveData<String> mText;

    public UserInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setAge(String age) {
        user.setAge(age);
        notifyPropertyChanged(com.example.museumhunt.BR.location);

    }
    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid";
    @Bindable
    private String toastMessage = null;
    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(com.example.museumhunt.BR.toastMessage);
    }
    @Bindable
    public String getUserAge() {
        return user.getAge();
    }

    @Bindable
    public String getLocation() {
        return user.getLocation();
    }

    public void setLocation(String password) {
        user.setLocation(password);
        notifyPropertyChanged(com.example.museumhunt.BR.location);
    }

    public void onLoginClicked() {
        setToastMessage(successMessage);
    }

}
