package com.example.organizarty.app.components;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizarty.R;
import com.example.organizarty.app.party.views.activities.PartyDetailActivity;
import com.example.organizarty.app.users.entities.UserEntity;
import com.example.organizarty.exceptions.AnauthenticatedUserException;
import com.example.organizarty.utils.storage.PreferencesUtils;

public class NavComponents {

    public static void setupNav(AppCompatActivity context, PreferencesUtils preferences) throws AnauthenticatedUserException {
        TextView txtname = context.findViewById(R.id.textUsername);
        TextView txtlogoff = context.findViewById(R.id.nav_logoff);

        UserEntity user = preferences.readOrganizartyAuthToken();

        txtlogoff.setOnClickListener(_v ->{
                preferences.clearOrganizartyUserInfo();

                Intent intent = new Intent(context, PartyDetailActivity.class);

                context.startActivity(intent);
            }
        );

        txtname.setText(user.fullname);
    }
}
