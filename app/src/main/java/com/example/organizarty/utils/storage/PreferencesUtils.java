package com.example.organizarty.utils.storage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.exceptions.AnauthenticatedUserException;

import java.util.jar.Pack200;

public class PreferencesUtils {
    private final AppCompatActivity _context;
    private final SharedPreferences _sharedPreferences;
    private static final String ORGANIZARTY_TOKEN_KEY = "ORGANIZARTY_AUTH_TOKEN";
    private static final String ORGANIZARTY_USER_NAME = "ORGANIZARTY_USER_NAME";
    private static final String ORGANIZARTY_USER_EMAIL = "ORGANIZARTY_USER_EMAIL";
    public PreferencesUtils(AppCompatActivity context)
    {
        _context = context;
        _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(_context);
    }

    private String readFromPreferences(String key)
    {
        return _sharedPreferences.getString(key, null);
    }

    @SuppressLint("ApplySharedPref")
    private void addToPreferences(String key, String value){
        SharedPreferences.Editor editor = _sharedPreferences.edit();

        editor.putString(key, value);

        editor.commit();
    }

    @SuppressLint("ApplySharedPref")
    private void removeFromPreferences(String key){
        SharedPreferences.Editor editor = _sharedPreferences.edit();

        editor.remove(key);

        editor.commit();
    }

    public void writeOrganizartyUserInfo(String token, String username, String email)
    {
        addToPreferences(ORGANIZARTY_TOKEN_KEY, token);
        addToPreferences(ORGANIZARTY_USER_NAME, username);
        addToPreferences(ORGANIZARTY_USER_EMAIL, email);
    }

    public void clearOrganizartyUserInfo()
    {
        removeFromPreferences(ORGANIZARTY_TOKEN_KEY );
        removeFromPreferences(ORGANIZARTY_USER_NAME);
        removeFromPreferences(ORGANIZARTY_USER_EMAIL);
    }

    public UserEntity readOrganizartyAuthToken() throws AnauthenticatedUserException {
        String token = readFromPreferences(ORGANIZARTY_TOKEN_KEY);
        if(token == null){
            clearOrganizartyUserInfo();
            throw new AnauthenticatedUserException();
        }

        String name = readFromPreferences(ORGANIZARTY_USER_NAME);
        if(name.isEmpty()){
            clearOrganizartyUserInfo();
            throw new AnauthenticatedUserException();
        }

        String email = readFromPreferences(ORGANIZARTY_USER_EMAIL);
        if(email == null){
            clearOrganizartyUserInfo();
            throw new AnauthenticatedUserException();
        }

        return new UserEntity(name, email, token);
    }
}
