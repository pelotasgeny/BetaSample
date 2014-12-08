package com.example.applause.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebViewClient;

import com.example.applause.R;
import com.example.applause.databinding.ActivityContactDetailBinding;
import com.example.applause.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class ContactDetailActivity extends ActionBarActivity implements ContantDetailsContract.View {

    ActivityContactDetailBinding binding;
    ContantDetailsContract.Presenter contractDetailsPresenter;

    public static void start(Context context, User user) {
        Intent intent = new Intent(context, ContactDetailActivity.class);
        intent.putExtra("user", new Gson().toJson(user));

        context.startActivity(intent);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_detail);

        User user = new Gson().fromJson(getIntent().getStringExtra("user"), User.class);

        contractDetailsPresenter = new ContractDetailsPresenter(user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contractDetailsPresenter.bind(this);
    }

    @Override
    protected void onPause() {
        contractDetailsPresenter.unbind();
        super.onPause();
    }

    @Override
    public void setHeader(String title) {
        setTitle(title);
    }

    @Override
    public void setFirstName(String firstName) {
        binding.viewContactDetails.contactsDetailFirstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        binding.viewContactDetails.contactsDetailLastName.setText(lastName);
    }

    @Override
    public void setEmail(String email) {
        binding.viewContactDetails.contactsDetailEmail.setText(email);
    }

    @Override
    public void loadPicture(String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.contact_placeholder)
                .into(binding.viewContactDetails.contactsDetailPicture);
    }

    @Override
    public void setLocationStreet(String street) {
        binding.viewContactDetails.contactsDetailLocationStreet.setText(street);
    }

    @Override
    public void setLocationCity(String city) {
        binding.viewContactDetails.contactsDetailLocationCity.setText(city);
    }

    @Override
    public void setLocationState(String state) {
        binding.viewContactDetails.contactsDetailLocationState.setText(state);
    }

    @Override
    public void setLocationZip(String zip) {
        binding.viewContactDetails.contactsDetailLocationZip.setText(zip);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void setLocationMap(String loadUrl) {
        binding.viewContactMap.webView.getSettings().setJavaScriptEnabled(true);
        binding.viewContactMap.webView.setWebViewClient(new WebViewClient());
        binding.viewContactMap.webView.loadUrl(loadUrl);
    }

    @Override
    public void setEasterEgg(View.OnTouchListener onTouchListener) {
        binding.viewContactDetails.contactsDetailPicture.setOnTouchListener(onTouchListener);
    }
}