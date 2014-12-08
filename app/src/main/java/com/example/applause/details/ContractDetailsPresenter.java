package com.example.applause.details;


import android.view.MotionEvent;
import android.view.View;

import com.example.applause.model.User;

import org.apache.commons.lang3.text.WordUtils;

public class ContractDetailsPresenter implements ContantDetailsContract.Presenter, View.OnTouchListener {


    private User user;

    public ContractDetailsPresenter(User user) {
        this.user = user;
    }

    @Override
    public void bind(ContantDetailsContract.View view) {
        view.setHeader(WordUtils.capitalize(user.name.first) + " " + WordUtils.capitalize(user.name.last));

        view.setFirstName(WordUtils.capitalize(user.name.first));
        view.setLastName(WordUtils.capitalize(user.name.last));

        view.loadPicture(user.picture.medium);
        view.setLocationStreet(WordUtils.capitalize(user.location.street));
        view.setLocationCity(WordUtils.capitalize(user.location.city));
        view.setLocationState(WordUtils.capitalize(user.location.state));
        view.setLocationZip(WordUtils.capitalize(user.location.zip));

        view.setEmail(user.email);

        view.setLocationMap("https://maps.google.com/" + "?q=" + user.location.city + "," + user.nat);

        view.setEasterEgg(this);
    }

    @Override
    public void unbind() {
        // nop
    }

    private int count = 0;
    private long startMillis = 0;

    public boolean onTouch(View v, MotionEvent event) {
        int eventaction = event.getAction();

        if (eventaction == MotionEvent.ACTION_DOWN) {
            //get system current milliseconds
            long time = System.currentTimeMillis();

            //if it is the first time, or if it has been more than 3 seconds since the first tap ( so it is like a new try), we reset everything
            if (startMillis == 0 || (time - startMillis > 3000)) {
                startMillis = time;
                count = 1;
            }
            //it is not the first, and it has been  less than 3 seconds since the first
            else { //  time-startMillis< 3000
                count++;
            }

            if (count == 5) {
                throw new RuntimeException("Easter egg!");
            }
            return true;
        }
        return false;
    }
}
