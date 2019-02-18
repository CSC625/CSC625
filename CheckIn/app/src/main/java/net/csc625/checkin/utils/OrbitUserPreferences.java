package net.csc625.checkin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.csc625.checkin.models.pojos.User;

import java.util.List;

/**
 * Created by Kevin Stanley on 11/7/2017.
 */

public class OrbitUserPreferences {
    private static Context context;
    static final private String prefName = "Orbit";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    /***
     *
     * SendBird
     *
     */


    private static final String PREFERENCE_KEY_USER_ID = "userId";
    private static final String PREFERENCE_KEY_NICKNAME = "nickname";
    private static final String PREFERENCE_KEY_PROFILE_URL = "profileUrl";
    private static final String PREFERENCE_KEY_CONNECTED = "connected";

    private static final String PREFERENCE_KEY_NOTIFICATIONS = "notifications";
    private static final String PREFERENCE_KEY_NOTIFICATIONS_SHOW_PREVIEWS = "notificationsShowPreviews";
    private static final String PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB = "notificationsDoNotDisturb";
    private static final String PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB_FROM = "notificationsDoNotDisturbFrom";
    private static final String PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB_TO = "notificationsDoNotDisturbTo";
    private static final String PREFERENCE_KEY_GROUP_CHANNEL_DISTINCT = "channelDistinct";


    /***
     *
     * SendBird
     *
     */

    public OrbitUserPreferences(Context context)
    {
        this.context = context;
        pref = this.context.getSharedPreferences(prefName, 0);
        editor = pref.edit();
    }

    /**
     * Storing object in shared preferences
     * @param prefName
     * @param type
     */
    public <T> void storePreference(String prefName, T type)
    {
        Gson gson = new Gson();
        String json = gson.toJson(type);
        editor.putString(prefName, json);
        editor.commit();
    }

    /**
     * Store student list
     * @param prefName
     * @param list
     */
    public <T> void storeListPreference(String prefName, List<T> list)
    {
        String json = new Gson().toJson(list, new TypeToken<List<T>>(){}.getType());
        editor.putString(prefName, json);
        editor.commit();
    }

    /**
     * Getting text from shared preferences
     * @param prefName
     * @return
     */
    public String getStringPreference(String prefName)
    {
        String prefValue = pref.getString(prefName, null);
        return prefValue;
    }

    /**
     * Getting object from user preferences
     * @param prefName
     * @return
     */
    public User getUserPreferenceObj(String prefName)
    {
        Gson gson = new Gson();
        String json = pref.getString(prefName, "");
        User user = gson.fromJson(json, User.class);
        if (user != null) {
            return user;
        }
        return null;
    }

    /**
     * Getting object from teacher preferences
     * @param prefName
     * @return
     */
    /*public Teacher getTeacherPreferenceObj(String prefName)
    {
        Gson gson = new Gson();
        String json = pref.getString(prefName, "");
        Teacher teacher = gson.fromJson(json, Teacher.class);
        if (teacher != null) {
            return teacher;
        }
        return null;
    }*/

    /****
     *
     *
     * SendBird SPs
     *
     */

    public static void init(Context appContext) {
        context = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public static void setUserId(String userId) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_USER_ID, userId).apply();
    }

    public static String getUserId() {
        return getSharedPreferences().getString(PREFERENCE_KEY_USER_ID, "");
    }

    public static void setNickname(String nickname) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_NICKNAME, nickname).apply();
    }

    public static String getNickname() {
        return getSharedPreferences().getString(PREFERENCE_KEY_NICKNAME, "");
    }

    public static void setProfileUrl(String profileUrl) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_PROFILE_URL, profileUrl).apply();
    }

    public static String getProfileUrl() {
        return getSharedPreferences().getString(PREFERENCE_KEY_PROFILE_URL, "");
    }

    public static void setConnected(boolean tf) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_CONNECTED, tf).apply();
    }

    public static boolean getConnected() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_CONNECTED, false);
    }

    public static void clearAll() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear().apply();
    }

    public static void setNotifications(boolean notifications) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_NOTIFICATIONS, notifications).apply();
    }

    public static boolean getNotifications() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_NOTIFICATIONS, true);
    }

    public static void setNotificationsShowPreviews(boolean notificationsShowPreviews) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_NOTIFICATIONS_SHOW_PREVIEWS, notificationsShowPreviews).apply();
    }

    public static boolean getNotificationsShowPreviews() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_NOTIFICATIONS_SHOW_PREVIEWS, true);
    }

    public static void setNotificationsDoNotDisturb(boolean notificationsDoNotDisturb) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB, notificationsDoNotDisturb).apply();
    }

    public static boolean getNotificationsDoNotDisturb() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB, false);
    }

    public static void setNotificationsDoNotDisturbFrom(String notificationsDoNotDisturbFrom) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB_FROM, notificationsDoNotDisturbFrom).apply();
    }

    public static String getNotificationsDoNotDisturbFrom() {
        return getSharedPreferences().getString(PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB_FROM, "");
    }

    public static void setNotificationsDoNotDisturbTo(String notificationsDoNotDisturbTo) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB_TO, notificationsDoNotDisturbTo).apply();
    }

    public static String getNotificationsDoNotDisturbTo() {
        return getSharedPreferences().getString(PREFERENCE_KEY_NOTIFICATIONS_DO_NOT_DISTURB_TO, "");
    }
    public static void setGroupChannelDistinct(boolean channelDistinct) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_GROUP_CHANNEL_DISTINCT, channelDistinct).apply();
    }

    public static boolean getGroupChannelDistinct() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_GROUP_CHANNEL_DISTINCT, true);
    }


    /****
     *
     *
     * SendBird SPs
     *
     */


    /**
     * Clear shared preferences
     */
    public void clear(){
        editor.clear();
        editor.commit();
    }

    public void clear(String prefName)
    {
        editor.remove(prefName);
        editor.commit();
    }
}
