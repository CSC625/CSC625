package net.csc625.checkin.utils;

/**
 * Created by sristic on 12/5/17.
 */

public class Constants {

    // Date formating
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String DATE_FORMAT_LAST_LOGIN = "yyyy-mm-dd";

    // User
    public static final int USER_INVALID_ATTEMPTS = 0;
    public static final String USER_ACTIVE = "True";

    // Roles
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_TEACHER = "Teacher";
    public static final String ROLE_PARENT = "Parent";
    public static final String ROLE_STUDENT = "Student";
    public static final String DEFAULT = "Default";

    public static final String ORBIT_API_URL = "orbit.api.url";
    public static final String SENDBIRD_APPID = "orbit.sendbird.appid";
    public static final String APP_PROPERTIES = "app.properties";

    public static final String SUCCESS_STATUS = "SUCCESS";
    public static final String FAIL_STATUS = "FAILURE";

    // String for values that are not filled out thru app UI
    public static final String FILLOUT_LATER = "TO BE FILLED AT A LATER DATE";

    // Issue (Bug) Priority
    public static final String ISSUE_LOW_PRIORITY = "Low";
    public static final String ISSUE_MEDIUM_PRIORITY = "Medium";
    public static final String ISSUE_HIGH_PRIORITY = "High";

    // Dim bacground value
    public static final float DIM_BACKGROUND = 0.5f;
}