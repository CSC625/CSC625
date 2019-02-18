package net.csc625.checkin.services;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import net.csc625.checkin.models.dto.AccountDetailsDTO;
import net.csc625.checkin.models.exceptions.ErrorResponse;
import net.csc625.checkin.models.pojos.User;
import net.csc625.checkin.utils.OrbitRestClient;
import net.csc625.checkin.utils.OrbitUserPreferences;
import net.csc625.checkin.utils.ServerCallback;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by sristic on 12/4/17.
 */

public class UserService extends BaseService {
    private Context context;

    public UserService(Context context){
        this.context = context;
    }


    public void addUser(AccountDetailsDTO accountDetails, final ServerCallback<User> callback){
        Gson gson = new Gson();
        String json = gson.toJson(accountDetails);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.post(this.context, "add-user", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject jsonUser) {
                        // called when success happens
                        Log.i("RegisterActivity", "Successfully added new user: " + jsonUser);
                        Gson gson = new Gson();
                        User user = gson.fromJson(jsonUser.toString(), User.class);
                        callback.onSuccess(user);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("RegisterActivity", "Error when adding new user: " + errorResponse);
                        Gson gson = new Gson();
                        ErrorResponse er = gson.fromJson(errorResponse.toString(), ErrorResponse.class);
                        callback.onFail(er);
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
    }

    /**
     * Find user by Firebase UID
     * @param uid
     * @param savetoSP - Save to Shared Preferences
     */
    public void findUserByUID(final String uid, final boolean savetoSP, final ServerCallback<User> callback){
        if (uid == null) {
            return;

        }
        /*String url = "get-user/" + uid;
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.get(url, null, new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject user) {
                        Log.i("UserService", "Successfully found a user: " + user);
                        Gson gson = new Gson();
                        User dbUser = gson.fromJson(user.toString(), User.class);
                        if (savetoSP) {
                            callback.onSuccess(dbUser);
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("UserService", "Error when finding user: " + errorResponse);
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });*/
    }

    public void storeUserInPreferences(FirebaseAuth mAuth, final ServerCallback<Boolean> callback) {
        FirebaseUser user = mAuth.getCurrentUser();
        final OrbitUserPreferences orbitPref = new OrbitUserPreferences(this.context);
        callback.onSuccess(true);
        /*this.findUserByUID(user.getUid(), true, new ServerCallback<User>() {
            @Override
            public void onSuccess(User dbUser) {
                orbitPref.storePreference("loggedUser", dbUser);
                callback.onSuccess(true);
            }

            @Override
            public void onFail(ErrorResponse errorMessage) {

            }
        });*/
    }

}
