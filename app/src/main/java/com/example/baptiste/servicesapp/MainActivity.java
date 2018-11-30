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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

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
        CreateUserFile("/pasdrus1.txt");
    }

    public void CreateUserFile(String file){
        String filePath = MainActivity.tcontext.getFilesDir().getPath()+file;
        File f = new File(filePath);
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            SharedPreferences.Editor editor = MainActivity.tcontext.getSharedPreferences("Preferences",0).edit();
            editor.putString("Service",filePath);
            editor.apply();
        }
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
        alertDialogBuilder.setMessage(
                "Ceci est un test" + restoredText+
                        "\n\nmemoire utilisé : " + logMemoryInfo() + " Mo"+
                        "\n\nCPU : " + readUsage()* 100 + " %");
        alertDialogBuilder.setPositiveButton("OK", listener);
        alertDialogBuilder.show();

    }

    private float logMemoryInfo() {
        long mb = 1024;
        Runtime runtime = Runtime.getRuntime();
        float mem = (runtime.totalMemory() - runtime.freeMemory()) / mb;

        return mem;
    }

    private float readUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();

            String[] toks = load.split(" ");

            long idle1 = Long.parseLong(toks[5]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            reader.seek(0);
            load = reader.readLine();
            reader.close();

            toks = load.split(" ");

            long idle2 = Long.parseLong(toks[5]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }


    public boolean onOptionsItemSelected(MenuItem item){

        return super.onOptionsItemSelected(item);
    }

    public static String loadJSONFromAsset(String file){
        String json = null;
        try{
            InputStream is = tcontext.getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer,"UTF-8");

        }catch (IOException e){e.printStackTrace();}
        return json;
    }

    public static String ReadFile(String file){
        String filePath = MainActivity.tcontext.getFilesDir().getPath() + file;
        File f = new File(filePath);

        StringBuilder text = null;
        try {
            //Read text from file
            text = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }catch (IOException e) {e.printStackTrace();}
        return text.toString();
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
                case 2: // Fragment # 1 - This will show SecondFragment
                    return UserFragment.newInstance(1);
                    //return SecondFragment.newInstance(1,"Test");
                case 3: // Fragment # 1 - This will show SecondFragment
                    //return Developper_page.newInstance(1);
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
