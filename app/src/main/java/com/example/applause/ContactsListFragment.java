package com.example.applause;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applause.model.Results;
import com.example.applause.model.User;
import com.example.applause.network.ContactsRetrofitService;
import com.example.applause.network.ContactsRetrofitSpiceRequest;
import com.example.applause.view.ContactsAdapter;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactsListFragment extends Fragment implements View.OnClickListener {


    private SpiceManager spiceManager = new SpiceManager(ContactsRetrofitService.class);

    private RecyclerView mRecyclerView;
    TextView footer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        footer = (TextView) view.findViewById(R.id.footer);
        footer.setOnClickListener(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), getResources().getInteger(R.integer.column_count));
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)


        ContactsRetrofitSpiceRequest contactsRetrofitSpiceRequest = new ContactsRetrofitSpiceRequest();
        spiceManager.execute(contactsRetrofitSpiceRequest, "contacts", DurationInMillis.ALWAYS_RETURNED, new ListContributorRequestListener());
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
        super.onStop();
    }

    public void update(List<User> users) {
        ContactsAdapter mAdapter = new ContactsAdapter(users);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext().getPackageName() + "/feedback");
        intent.putExtra("TITLE",  "How do you like our Random Selection?");
        intent.putExtra("RATING_TITLE", "How do you like our Random Selection?");
        intent.putExtra("HINT", "Let us know if you love the random selection");
        String tags[] = new String[]{ "random", "taytay", "droid"};
        intent.putExtra("TAGS", tags);
        v.getContext().sendBroadcast(intent);
    }

    public final class ListContributorRequestListener implements RequestListener<Results> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Results result) {
            update(result.results);
        }
    }
}
