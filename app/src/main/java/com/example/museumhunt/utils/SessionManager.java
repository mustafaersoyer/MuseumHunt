package com.example.museumhunt.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.museumhunt.MainActivity;

import java.util.HashMap;

public class SessionManager {

    // SharedPreferences nesnesi
    SharedPreferences pref;

    //Shared preferences için Editor nesnesi
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mod
    int PRIVATE_MODE = 0;

    // Sharedpref dosya adı
    private static final String PREF_NAME = "SharedPreferencesOrnek";

    //SharedPreferences için key-value'lar.
    private static final String IS_LOGIN = "IsLoggedIn";

    //email ve sifre keylerini her yerden ulaşılabilmesi için public yapıyoruz.
    public static final String KEY_GENDER = "male";

    public static final String KEY_LOCATION = "111";

    public static final String KEY_AGE = "25";

    // Yapıcı fonksiyonumuz
    public SessionManager(Context context){

        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * login session'ı oluşturuyoruz. Ilk defa gelen veriyi kaydediyoruz.
     * */
    public void createLoginSession(String age,String location, String gender){

        // giriş yapıldığında login değerini true yapıyoruz.
        editor.putBoolean(IS_LOGIN, true);

        // email ve sifreyi editor ile kaydediyoruz.
        editor.putString(KEY_AGE, age);

        editor.putString(KEY_GENDER, gender);

        editor.putString(KEY_LOCATION, location);

        // değişiklikleri yolluyoruz.
        editor.commit();
    }

    /**
     * Kullanıcının login durumunu kontrol etmek
     * Eger false ise girmesi yasak olan yerden çıkartmak
     * true ise girebilmesini sağlamak.
     * */
    public void checkLogin(){

        if(!this.isLoggedIn()){

            // Kullanıcı girmemesi gereken yerde, giriş sayfasına yönlendir.
            Intent i = new Intent(_context, MainActivity.class);
            // flagler ile her şeyi sil
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    /**
     * Kaydedilen verileri çağırma,
     * */
    public HashMap<String, String> getUserDetails(){

        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_AGE, pref.getString(KEY_AGE, null));

        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        user.put(KEY_LOCATION, pref.getString(KEY_LOCATION, null));

        return user;
    }

    /**
     * çıkış yapıldığında session bilgilerini sil.
     * */
    public void logoutUser(){

        // Shared Preferences dan tüm verileri sil.
        editor.clear();
        editor.commit();

        // çıkıştan sonra giriş ekranına yönlendir.
        Intent i = new Intent(_context, MainActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    /**
     * Giriş için hızlı kontrol.
     * **/
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}