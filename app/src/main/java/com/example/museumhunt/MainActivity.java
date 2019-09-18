package com.example.museumhunt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.museumhunt.UI.user_info.UserInfoActivity;
import com.example.museumhunt.Utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SessionManager session;
    String location,age,gender;
    public NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_settings)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        sessionCheck();

    }


    void sessionCheck(){
        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();

        location = user.get(SessionManager.KEY_LOCATION);
        gender = user.get(SessionManager.KEY_GENDER);
        age = user.get(SessionManager.KEY_AGE);

        if (location==null || gender==null || age==null){
            startActivity(new Intent(getApplicationContext(), UserInfoActivity.class));
        }
        else{
            Toast.makeText(this, ""+location+" "+gender+" "+age, Toast.LENGTH_SHORT).show();
        }
    }
}
