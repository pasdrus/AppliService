package com.example.baptiste.servicesapp;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baptiste.servicesapp.ItemInfosFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class ItemInfosRecyclerViewAdapter extends RecyclerView.Adapter<ItemInfosRecyclerViewAdapter.ViewHolder> {

    private final List<ServicesInfos.ServicesItemInfos> mValues1;
    private final OnListFragmentInteractionListener mListener1;

    public ItemInfosRecyclerViewAdapter(List<ServicesInfos.ServicesItemInfos> items, OnListFragmentInteractionListener listener) {
        mValues1 = items;
        mListener1 = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_infos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.mContentView.setText(mValues1.get(position).firstValue);
        holder.mContentTestView.setText(mValues1.get(position).section);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener1) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener1.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final TextView mContentTestView;
        public ServicesInfos.ServicesItemInfos mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mContentTestView = (TextView) view.findViewById(R.id.textTest);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
