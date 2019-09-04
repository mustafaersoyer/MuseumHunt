package com.example.museumhunt.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.museumhunt.MainActivity;
import com.example.museumhunt.R;
import com.example.museumhunt.SessionManager;
import com.example.museumhunt.databinding.InfoBinding;


public class UserInfo extends AppCompatActivity {

    SessionManager session;
    Button btnSave;
    UserInfoViewModel userInfoVM;
    RadioButton radioButton;
    RadioGroup radioGroup;
    Spinner spinner;
    String location,age,gender;
    EditText editTextAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.info);
        InfoBinding infoBinding = DataBindingUtil.setContentView(this, R.layout.info);
        infoBinding.setViewModel(new UserInfoViewModel());
        infoBinding.executePendingBindings();

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn() == true){
            startActivity(new Intent(UserInfo.this, MainActivity.class));
        }

        viewBind();

        userInfoVM = new UserInfoViewModel();




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                location=adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                
            }

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfoVM.setGender(radioButton.getText().toString());
                userInfoVM.setLocation(location);
                userInfoVM.setAge(editTextAge.getText().toString());

                age=userInfoVM.getUserAge();
                gender=userInfoVM.getGender();

                if(gender!=null && userInfoVM.getLocation()!=null && age!=null) {
                    session.createLoginSession(age, userInfoVM.getLocation(), gender);
                    startActivity(new Intent(UserInfo.this, MainActivity.class));
                }
                else{
                    Toast.makeText(UserInfo.this, "age: "+age+" gender "+gender + " Loc "+ location, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    void viewBind(){

        radioGroup = findViewById(R.id.rg);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton =  findViewById(R.id.radioMale);
        spinner = findViewById(R.id.spinnerLocations);
        btnSave = findViewById(R.id.btnInfo);
        editTextAge = findViewById(R.id.editTextAge);
    }
}
