package com.example.baptiste.servicesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.baptiste.servicesapp.ItemInfosFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private static String LastService = "Netflix";
    private ArrayList al = new ArrayList();
    static public HashMap<String, View> mapper = new HashMap<>();

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
        String type = mValues.get(position).type;
        String service = mValues.get(position).serviceString;
        SharedPreferences prefs = MainActivity.tcontext.getSharedPreferences("Preferences", 0);
        String restoredText = prefs.getString("Service", null);

        if(service.equals(restoredText)) {
            switch (type) {
                case "edit":
                    EditText editText = new EditText(holder.mLayout.getContext());
                    editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    try {
                        editText.setHint(mValues.get(position).firstValue.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    holder.mLayout.addView(editText);
                    al.add(holder.mLayout.getChildAt(0));
                    break;
                case "label":
                    TextView label = new TextView(holder.mLayout.getContext());
                    label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    try {
                        label.setText(mValues.get(position).firstValue.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    holder.mLayout.addView(label);
                    al.add(holder.mLayout.getChildAt(0));
                    break;
                case "radioGroup":
                    RadioGroup radioGroup = new RadioGroup(holder.mLayout.getContext());
                    radioGroup.setOrientation(LinearLayout.HORIZONTAL);
                    radioGroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    for(int i = 0; i < mValues.get(position).firstValue.length(); i++) {
                        RadioButton radioButton = new RadioButton(MainActivity.tcontext);
                        radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        try {
                            radioButton.setText(mValues.get(position).firstValue.getString(i));

                            radioButton.setHint(mValues.get(position).section);

                            radioButton.setId(position);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        radioGroup.addView(radioButton);
                    }
                    holder.mLayout.addView(radioGroup);
                    al.add(holder.mLayout.getChildAt(0));
                    break;
                case "button":
                    CheckBox bouton = new CheckBox(holder.mLayout.getContext());
                    bouton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    try {
                        bouton.setText(mValues.get(position).firstValue.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    holder.mLayout.addView(bouton);
                    al.add(holder.mLayout.getChildAt(0));
                    break;
                case "switch":
                    Switch sw = new Switch(holder.mLayout.getContext());
                    try {
                        sw.setHint(mValues.get(position).firstValue.getString(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    sw.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    sw.setText(mValues.get(position).section);
                    try {
                        sw.setChecked(mValues.get(position).firstValue.getBoolean(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    holder.mLayout.addView(sw);
                    al.add(holder.mLayout.getChildAt(0));
                    break;
            }



            if(mValues.get(position).end){
                Button bouton = new Button(holder.mLayout.getContext());
                bouton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bouton.setText("Valider");
                holder.mLayout.addView(bouton);

                bouton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        listTest();
                        MainActivity.TestRefresh(MainActivity.sViewPager);
                        MainActivity.setCurrentItem(2,true);
                    }
                });
            }
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void listTest() {
        SharedPreferences prefs = MainActivity.tcontext.getSharedPreferences("Preferences", 0);
        String restoredText = prefs.getString("Service", null);
        String filePath = MainActivity.tcontext.getFilesDir().getPath()+"/pasdrus1.txt";
        File f = new File(filePath);
        JSONObject utilisateur = new JSONObject();
        JSONObject valueUser = new JSONObject();
        JSONArray UserTab = new JSONArray();
        JSONArray ServiceTab = new JSONArray();
        JSONObject User = new JSONObject();
        ArrayList<JSONObject> ServList = new ArrayList<>();

        String jsonString = MainActivity.ReadFile("/pasdrus1.txt");
        if(jsonString != "") {
            try {
                JSONObject obj = new JSONObject(jsonString);
                JSONArray service = obj.getJSONArray("users");
                for(int i = 0; i < service.length(); i++){
                    JSONObject serv = service.getJSONObject(i);
                    if(serv.get("Service").toString().equals(restoredText)) {
                        UserTab = serv.getJSONArray("Values");
                    }else{
                        ServList.add(serv);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for(int i =0;i<al.size();i++){
            if(al.get(i) instanceof EditText){

                try {
                    utilisateur.put(((EditText) al.get(i)).getHint().toString(),((EditText) al.get(i)).getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(al.get(i) instanceof RadioGroup){
                try {
                    RadioGroup trueRadio = ((RadioGroup) al.get(i));
                    RadioButton newView = null;
                    int x = trueRadio.getChildCount();

                    for(int y = 0; y<x;y++){
                        boolean checked = ((RadioButton) trueRadio.getChildAt(y)).isChecked();
                        if(checked){
                            newView = (RadioButton) trueRadio.getChildAt(y);
                        }
                    }
                    utilisateur.put(newView.getHint().toString(),newView.getText().toString());
                }catch (JSONException e) {
                  e.printStackTrace();
                }
            }else if(al.get(i) instanceof Switch){
                try {
                    if(((Switch) al.get(i)).isChecked()){
                        utilisateur.put(((Switch) al.get(i)).getText().toString(),"Yes");
                    }else {
                        utilisateur.put(((Switch) al.get(i)).getText().toString(),"No");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if(al.get(i) instanceof CheckBox){
                try {
                    if(((CheckBox) al.get(i)).isChecked()){
                        utilisateur.put(((CheckBox) al.get(i)).getText().toString(),"Yes");
                    }else {
                        utilisateur.put(((CheckBox) al.get(i)).getText().toString(),"No");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        try {

            UserTab.put(utilisateur);
            valueUser.put("Service", restoredText);
            valueUser.put("Values", UserTab);
            ServList.add(valueUser);
            for(JSONObject j : ServList){
                ServiceTab.put(j);
            }
            User.put("users", ServiceTab);

            FileOutputStream fOut = new FileOutputStream(f);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(User.toString());

            myOutWriter.close();

            fOut.flush();
            fOut.close();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public ServicesInfos.ServicesItemInfos mItem;
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
