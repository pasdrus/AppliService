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
import android.widget.TextView;

import com.example.baptiste.servicesapp.ItemInfosFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    private static String LastService = "Netflix";
    private ArrayList al = new ArrayList();

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
                        al.add(holder.mLayout.getChildAt(0));
                        break;
                    case "label":
                        TextView label = new TextView(holder.mLayout.getContext());
                        label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        label.setText(mValues.get(position).firstValue);
                        holder.mLayout.addView(label);
                        al.add(holder.mLayout.getChildAt(0));
                        break;
                    case "radioGroup":
                        RadioGroup radioGroup = new RadioGroup(holder.mLayout.getContext());
                        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        RadioButton radioButton = new RadioButton(MainActivity.tcontext);
                        radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        radioButton.setText(mValues.get(position).firstValue);
                        radioGroup.addView(radioButton);
                        holder.mLayout.addView(radioGroup);
                        al.add(holder.mLayout.getChildAt(0));
                        break;
                    case "button":
                        Button bouton = new Button(holder.mLayout.getContext());
                        bouton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        bouton.setText(mValues.get(position).firstValue);
                        holder.mLayout.addView(bouton);
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
                        /*SharedPreferences.Editor editor = holder.mLayout.getContext().getSharedPreferences("Preferences",0).edit();
                        editor.clear();
                        editor.apply();*/

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

    public void listTest(){
        //obj.put("name",((TextView) al.get(i)).getText().toString());
        String jsonString = MainActivity.loadJSONFromAsset("users.json");
        SharedPreferences prefs = MainActivity.tcontext.getSharedPreferences("Preferences", 0);
        String restoredText = prefs.getString("Service", null);

        for(int i =0;i<al.size();i++){
            if(al.get(i) instanceof EditText){



                try{

                    ////BORDEL POUR RENTRER DANS LE JSON :
                    //// AU CAS OU REGARDER : http://www.vogella.com/tutorials/AndroidJSON/article.html
                    ///// https://abhiandroid.com/programming/json
                    ///// https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
                    JSONObject obj = new JSONObject(jsonString);

                    JSONArray service = obj.getJSONArray("users");

                    for (int j = 0; j <= 2; j++) {

                        JSONObject serviceName = service.getJSONObject(j);

                        String serviceNameString = serviceName.getString("title");

                        JSONArray ServiceArray = serviceName.getJSONArray("elements");

                        ServiceArray.put(j,"Test");

                        /*if(serviceNameString.equals(restoredText)) {
                            for (int x = 0; x <= ServiceArray.length(); x++) {
                                JSONObject ServiceArray = ServiceArray.getJSONObject(x);

                                SharedPreferences.Editor editor = MainActivity.tcontext.getSharedPreferences("Preferences",0).edit();
                                editor.putString("Service",ServiceArray.getJSONObject(x).toString());
                                editor.apply();

                                ServiceArrayObject.put("name",((TextView) al.get(i)).getText().toString());
                                SharedPreferences.Editor editor1 = MainActivity.tcontext.getSharedPreferences("Users",0).edit();
                                editor1.putString("EditorText"+i,((TextView) al.get(i)).getText().toString());
                                editor1.apply();
                            }
                        }**/



                    }

                }catch (JSONException e){e.printStackTrace();}
            }else if(al.get(i) instanceof TextView){

            }else if(al.get(i) instanceof RadioButton){

            }else if(al.get(i) instanceof CheckBox){

            }
        }
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
