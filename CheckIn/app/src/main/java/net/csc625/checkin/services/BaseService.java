package net.csc625.checkin.services;

import android.content.Context;

import net.csc625.checkin.utils.Constants;
import net.csc625.checkin.utils.OrbitRestClient;
import net.csc625.checkin.utils.PropertiesService;

/**
 * Created by brocktubre on 2/22/18.
 */

public class BaseService {
    private PropertiesService propertiesService;
    private OrbitRestClient orbitRestClient;

    public PropertiesService getPropertiesService(Context context){
        if(this.propertiesService == null){
            return this.propertiesService = new PropertiesService(context);
        }
        return this.propertiesService;
    }

    public OrbitRestClient getOrbitRestClient(Context context){
        if(this.orbitRestClient == null){
            this.orbitRestClient = new OrbitRestClient(context);
            this.orbitRestClient.setBaseUrl(getPropertiesService(context).getProperty(context, Constants.ORBIT_API_URL));
            return this.orbitRestClient;
        }
        return this.orbitRestClient;
    }
}
