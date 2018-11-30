package com.example.baptiste.servicesapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Developper_page extends Fragment {
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static Developper_page newInstance(int page) {
        Developper_page developper_page = new Developper_page();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        developper_page.setArguments(args);
        return developper_page;
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
        View view = inflater.inflate(R.layout.fragment_developper_page, container, false);
        ImageView tvLabel = view.findViewById(R.id.developper1);
        tvLabel.setImageResource(R.drawable.developper1);
        ImageView tvLabel2 = view.findViewById(R.id.developper2);
        tvLabel2.setImageResource(R.drawable.developper2);
        ImageView tvLabel3 = view.findViewById(R.id.developper3);
        tvLabel3.setImageResource(R.drawable.developper3);
        return view;
    }
}

