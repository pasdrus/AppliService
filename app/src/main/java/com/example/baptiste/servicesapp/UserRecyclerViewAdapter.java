package com.example.baptiste.servicesapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baptiste.servicesapp.UserFragment.OnListFragmentInteractionListener;

import org.json.JSONException;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private final List<User.UserItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    DialogInterface.OnClickListener listener;

    public UserRecyclerViewAdapter(List<User.UserItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        LinearLayout mLayoutsub = new LinearLayout(MainActivity.tcontext);
        mLayoutsub.setBottom(15);
        String s = "";
        for (User.UserSubItem usi : mValues.get(position).list) {
            String service = usi.service;

            SharedPreferences prefs = MainActivity.tcontext.getSharedPreferences("Preferences", 0);
            String restoredText = prefs.getString("Service", null);

            if (service.equals(restoredText)) {
                s = s.concat(usi.name+ " : "+ usi.value+"\n");
                //mLayoutsub.addView(label);
            }
        }
        TextView label = new TextView(holder.mLayout.getContext());
        label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        label.setText(s);
        holder.mLayout.addView(label);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public User.UserItem mItem;
        public final LinearLayout mLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mLayout =(LinearLayout) view.findViewById(R.id.linearlayout);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
