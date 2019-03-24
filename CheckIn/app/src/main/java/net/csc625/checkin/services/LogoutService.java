package net.csc625.checkin.services;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

import net.csc625.checkin.activities.LoginActivity;
import net.csc625.checkin.models.pojos.User;
import net.csc625.checkin.utils.Constants;
import net.csc625.checkin.utils.OrbitUserPreferences;

/**
 * Created by sristic on 1/31/18.
 */

public class LogoutService {

    private Context context;

    public LogoutService(Context context) {
        this.context = context;
    }

    public void logout() {
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(this.context);
        final User user = orbitPref.getUserPreferenceObj("loggedUser");

        FirebaseAuth.getInstance().signOut();

        orbitPref.clear("loggedUser");
        // Hoping this kills all previous activities
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        //context.startActivity(LoginActivity.createIntent(context));
    }

    private void disconnect() {
        /*SendBird.unregisterPushTokenAllForCurrentUser(new SendBird.UnregisterPushTokenHandler() {
            @Override
            public void onUnregistered(SendBirdException e) {
                if (e != null) {
                    // Error!
                    e.printStackTrace();

                    // Don't return because we still need to disconnect.
                } else {
//                    Toast.makeText(MainActivity.this, "All push tokens unregistered.", Toast.LENGTH_SHORT).show();
                }

                ConnectionManager.logout(new SendBird.DisconnectHandler() {
                    @Override
                    public void onDisconnected() {
                        OrbitUserPreferences.setConnected(false);
                    }
                });
            }
        });*/
    }
}
