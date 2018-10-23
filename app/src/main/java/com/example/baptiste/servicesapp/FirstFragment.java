package com.example.baptiste.servicesapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class FirstFragment extends Fragment {
        // Store instance variables
        private String title;
        private int page;
        private String test;

        // newInstance constructor for creating fragment with arguments
        public static FirstFragment newInstance(int page, String title) {
            FirstFragment fragmentFirst = new FirstFragment();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle", title);
            fragmentFirst.setArguments(args);
            return fragmentFirst;
        }

        // Store instance variables based on arguments passed
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            page = getArguments().getInt("someInt", 0);
            title = getArguments().getString("someTitle");


            String jsonString = loadJSONFromAsset(getContext());
            try{

                ////BORDEL POUR RENTRER DANS LE JSON :
                //// AU CAS OU REGARDER : http://www.vogella.com/tutorials/AndroidJSON/article.html
                ///// https://abhiandroid.com/programming/json
                ///// https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
                JSONObject obj = new JSONObject(jsonString);

                JSONArray service = obj.getJSONArray("services");

                JSONObject netflix = service.getJSONObject(0);

                JSONArray netflixServiceArray = netflix.getJSONArray("elements");

                JSONObject netflixServiceArrayObject = netflixServiceArray.getJSONObject(1);

                test = netflixServiceArrayObject.getString("type");

            }catch (JSONException e){e.printStackTrace();}


        }

    public String loadJSONFromAsset(Context context){
        String json = null;
        try{
            InputStream is = context.getAssets().open("service.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer,"UTF-8");

        }catch (IOException e){e.printStackTrace();}
        return json;

    }
        // Inflate the view for the fragment based on layout XML
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_first, container, false);
            TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
            TextView tvLabel2 = (TextView) view.findViewById(R.id.tvLabel2);
            tvLabel.setText(page + " -- " + title);
            tvLabel2.setText(test);
            return view;
        }
    }

