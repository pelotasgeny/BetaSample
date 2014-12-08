package com.example.applause.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.applause.R;
import com.example.applause.model.User;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    private List<User> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ContactsAdapter(List<User> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_item, parent, false);

        ContactsViewHolder vh = new ContactsViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.attachUser(mDataset.get(position));

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
