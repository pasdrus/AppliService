package com.example.baptiste.servicesapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fuck extends Fragment{
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static Fuck newInstance(int page) {
        Fuck fuck = new Fuck();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        fuck.setArguments(args);
        return fuck;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 1);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fuck, container, false);
        ImageView tvLabel = (ImageView) view.findViewById(R.id.Fuck);
        tvLabel.setImageResource(R.drawable.fuck);
        return view;
    }
}

