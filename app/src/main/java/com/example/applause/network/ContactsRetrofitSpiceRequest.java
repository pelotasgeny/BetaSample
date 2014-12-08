package com.example.applause.network;


import com.example.applause.model.Results;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class ContactsRetrofitSpiceRequest extends RetrofitSpiceRequest<Results, RandomUserApi> {

    public ContactsRetrofitSpiceRequest() {
        super(Results.class, RandomUserApi.class);
    }

    @Override
    public Results loadDataFromNetwork() throws Exception {
        return getService().listUsers(100);
    }
}
