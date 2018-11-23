package com.example.baptiste.servicesapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    static FragmentPagerAdapter sFragmentPagerAdapter;
    static Context tcontext;
    static ViewPager sViewPager;
    DialogInterface.OnClickListener listener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        tcontext = getApplicationContext();
        sViewPager = findViewById(R.id.vpPager);
        sFragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        SharedPreferences.Editor editor = getSharedPreferences("Preferences",0).edit();
        editor.clear();
        editor.apply();
    }

    public static void TestRefresh(ViewPager vp){
        vp.setAdapter(sFragmentPagerAdapter);
    }

    public static void setCurrentItem(int item, boolean smoothScroll) {
        sViewPager.setCurrentItem(item, smoothScroll);
    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.myactionbar,menu);

        return true;

    }



    //Méthode appellée dans myactionbar
    //Important de mettre MenuItem au lieu de menu car c'est le bouton d'un menu? en tout cas
    //ne marche pas juste avec menu
    public void popupInfos(MenuItem menuItem)
    {
        SharedPreferences prefs = getSharedPreferences("Preferences", 0);
        String restoredText = prefs.getString("Service", null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Ceci est un test" + restoredText);
        alertDialogBuilder.setPositiveButton("OK", listener);
        alertDialogBuilder.show();

    }


    public boolean onOptionsItemSelected(MenuItem item){

        return super.onOptionsItemSelected(item);
    }

    public static String loadJSONFromAsset(){
        String json = null;
        try{
            InputStream is = tcontext.getAssets().open("service.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer,"UTF-8");

        }catch (IOException e){e.printStackTrace();}
        return json;

    }




    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;


        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return ItemFragment.newInstance(1);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ItemInfosFragment.newInstance(1);
                    //return FirstFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return Fuck.newInstance(1);
                case 3: // Fragment # 1 - This will show SecondFragment
                    return ItemInfosFragment.newInstance(1);
                case 4: // Fragment # 1 - This will show SecondFragment
                    return ItemInfosFragment.newInstance(1);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Services";
                case 1:
                    return "Informations";
                case 2:
                    return "Utilisateurs";
                case 3:
                    return "Developpeurs";
            }

            return "NULL";

            //return "Page nt " + position;
        }



    }
}
