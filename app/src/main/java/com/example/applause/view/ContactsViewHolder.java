package com.example.applause.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applause.details.ContactDetailActivity;
import com.example.applause.R;
import com.example.applause.model.Name;
import com.example.applause.model.User;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // each data item is just a string in this case

    @InjectView(R.id.contacts_item_name)
    TextView contactName;

    @InjectView(R.id.contacts_item_image)
    ImageView contactThumbnail;
    Context context;
    private User user;

    public ContactsViewHolder(View v) {
        super(v);

        context = v.getContext();

        ButterKnife.inject(this, v);

        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContactDetailActivity.start(context, user);
    }

    public void attachUser(User user) {

        this.user = user;

        Name name = user.name;
        contactName.setText(capitalizeFirstLetter(name.first) + " " + capitalizeFirstLetter(name.last));

        Picasso.with(contactName.getContext())
                .load(user.picture.thumbnail)
                .placeholder(R.drawable.contact_placeholder)
                .into(contactThumbnail);

    }

    public String capitalizeFirstLetter(String original) {
        if (original.length() == 0)
            return original;
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

}
