package net.csc625.checkin.utils;

import android.content.Context;


import java.util.Properties;

/**
 * Created by brocktubre on 11/5/17.
 */

public class PropertiesService {

    private Context context;
    private PropertyReader propertyReader;
    private Properties properties;

    public PropertiesService(Context context) {
        this.context = context;
    }

    public String getProperty(Context context, String propName) {
        this.context = context;
        this.propertyReader = new PropertyReader(context);
        this.properties = this.propertyReader.getMyProperties(Constants.APP_PROPERTIES);
        return this.properties.getProperty(propName);
    }
}
