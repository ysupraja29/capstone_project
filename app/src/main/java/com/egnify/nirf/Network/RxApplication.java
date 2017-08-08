package com.egnify.nirf.Network;

import android.app.Application;

/**
 * Created by cteegarden on 1/26/16.
 */
public class RxApplication extends Application {

    private NetworkService networkService;
    private NetworkService2 networkService2;

    @Override
    public void onCreate() {
        super.onCreate();

        networkService = new NetworkService();
        networkService2 = new NetworkService2();
    }
    public NetworkService2 getNetworkService2(){
        return networkService2;
    }
    public NetworkService getNetworkService(){
        return networkService;
    }


}
