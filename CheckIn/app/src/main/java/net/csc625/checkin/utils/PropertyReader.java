package net.csc625.checkin.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by brocktubre on 11/4/17.
 */

public class PropertyReader {
    private Context context;
    private Properties properties;

    public PropertyReader(Context context){
        this.context = context;
        properties = new Properties();
    }

    public Properties getMyProperties(String file){
        try{
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        return properties;
    }
}
