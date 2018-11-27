package com.example.baptiste.servicesapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baptiste.servicesapp.UserFragment.OnListFragmentInteractionListener;

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
        String nom = mValues.get(position).name;
        String service = mValues.get(position).serviceString;

        int fin = mValues.size();

        SharedPreferences prefs = MainActivity.tcontext.getSharedPreferences("Preferences", 0);
        String restoredText = prefs.getString("Service", null);

        TextView label = new TextView(holder.mLayout.getContext());
        label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        label.setText(mValues.get(position).name);
        holder.mLayout.addView(label);

        TextView label3 = new TextView(holder.mLayout.getContext());
        label3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        label3.setText(mValues.get(position).serviceString);
        holder.mLayout.addView(label3);

        SharedPreferences users = MainActivity.tcontext.getSharedPreferences("Users", 0);
        String Toast = users.getString("EditorText1", null);

        String test1 = ItemInfosRecyclerViewAdapter.mapper.get("Netflix1").toString();

        TextView label1 = new TextView(holder.mLayout.getContext());
        label1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        label1.setText(mValues.get(position).serviceString + " t " + mValues.get(position).name);
        holder.mLayout.addView(label1);

        /*TextView label1 = new TextView(holder.mLayout.getContext());
        label1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        label1.setText(Toast);
        holder.mLayout.addView(label1);
*/

        /*if (service.equals(restoredText)) {
            SharedPreferences users = MainActivity.tcontext.getSharedPreferences("Users", 0);
            String Toast = users.getString("EditorText1", null);

            TextView label1 = new TextView(holder.mLayout.getContext());
            label1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            label1.setText(Toast);
            holder.mLayout.addView(label1);
        }*/
    }


    @Override
    public int getItemCount(){
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public User.UserItem mItem;
        public final LinearLayout mLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImageView = (ImageView) view.findViewById(R.id.image);
            mLayout =(LinearLayout) view.findViewById(R.id.linearlayout);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
