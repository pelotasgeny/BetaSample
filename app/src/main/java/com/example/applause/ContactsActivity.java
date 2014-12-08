package com.example.applause;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class ContactsActivity extends ActionBarActivity {

    public static final String PLACEHOLDER = "placeholder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ContactsListFragment(), PLACEHOLDER)
                    .commit();
        }
    }


}
