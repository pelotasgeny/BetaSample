package com.example.applause.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class ContactsRetrofitService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "https://api.randomuser.me";


    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(RandomUserApi.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
