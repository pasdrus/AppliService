package com.example.baptiste.servicesapp;

import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.baptiste.servicesapp.ItemInfosFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class ItemInfosRecyclerViewAdapter extends RecyclerView.Adapter<ItemInfosRecyclerViewAdapter.ViewHolder> {

    private final List<ServicesInfos.ServicesItemInfos> mValues;
    private final OnListFragmentInteractionListener mListener;
    int i=0;

    public ItemInfosRecyclerViewAdapter(List<ServicesInfos.ServicesItemInfos> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_infos, parent, false);


        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {




        //holder.mContentView.setText(mValues1.get(position).firstValue);
        //holder.mContentTestView.setText(mValues1.get(position).section);
        String type = mValues.get(position).type;
        String service = mValues.get(position).serviceString;
        int fin = mValues.size();

        SharedPreferences prefs = MainActivity.tcontext.getSharedPreferences("Preferences", 0);
        String restoredText = prefs.getString("Service", null);

        if(service.equals(restoredText)) {


                switch (type) {
                    case "edit":
                        EditText editText = new EditText(holder.mLayout.getContext());
                        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        editText.setHint(mValues.get(position).firstValue);
                        holder.mLayout.addView(editText);
                        break;
                    case "label":
                        TextView label = new TextView(holder.mLayout.getContext());
                        label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        label.setText(mValues.get(position).firstValue);
                        holder.mLayout.addView(label);
                        break;
                    case "radioGroup":
                        RadioGroup radioGroup = new RadioGroup(holder.mLayout.getContext());
                        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        RadioButton radioButton = new RadioButton(MainActivity.tcontext);
                        radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        radioButton.setText(mValues.get(position).firstValue);
                        radioGroup.addView(radioButton);
                        holder.mLayout.addView(radioGroup);
                        break;
                    case "button":
                        Button bouton = new Button(holder.mLayout.getContext());
                        bouton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        bouton.setText(mValues.get(position).firstValue);
                        holder.mLayout.addView(bouton);
                        break;
                }

        }
            //met un bouton mais seulement à la fin
            //à voir si on peut mettre un bouton à chaque changement de service
            //genre boucle qui check jus'à changement et on rajoute un bouton juste avant
            //à check aussi le changement de
            /*if(position==fin-1){
                Button bouton = new Button(holder.mLayout.getContext());
                bouton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bouton.setText("Retour");
                bouton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = holder.mLayout.getContext().getSharedPreferences("Preferences",0).edit();
                        editor.clear();
                        editor.apply();
                        MainActivity.setCurrentItem(0,true);
                    }
                });
                holder.mLayout.addView(bouton);




       // }

/*
        if(mValues1.get(position).type.equals("edit")){

            Button btnTag = new Button(MainActivity.tcontext);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText("Button");

            holder.mLayout.addView(btnTag);
        }else{
            Button btnTag = new Button(MainActivity.tcontext);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText("mercé");

            holder.mLayout.addView(btnTag);
        }
        */


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        //public final TextView mContentTestView;
        public ServicesInfos.ServicesItemInfos mItem;
        public final LinearLayout mLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            //mContentTestView = (TextView) view.findViewById(R.id.textTest);
            mLayout =(LinearLayout) view.findViewById(R.id.linearlayout);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
