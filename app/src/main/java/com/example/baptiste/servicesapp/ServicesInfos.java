package com.example.baptiste.servicesapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.drawable.Drawable;


public class ServicesInfos {
    public static final List<ServicesItemInfos> ITEMS = new ArrayList<ServicesItemInfos>();
    public static final Map<String, ServicesItemInfos> ITEM_MAP = new HashMap<String, ServicesItemInfos>();


    private static void addItem(ServicesItemInfos item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ServicesItemInfos createServicesItem(int position, String section, String type, JSONArray firstValue, String serviceString, boolean end) {
        return new ServicesItemInfos(String.valueOf(position),section,type,firstValue,serviceString,end);
    }


    static {
        // Add some sample items.
        String jsonString = MainActivity.loadJSONFromAsset("service.json");
        for (int i = 0; i <= 2; i++) {

            try{

                ////BORDEL POUR RENTRER DANS LE JSON :
                //// AU CAS OU REGARDER : http://www.vogella.com/tutorials/AndroidJSON/article.html
                ///// https://abhiandroid.com/programming/json
                ///// https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
                JSONObject obj = new JSONObject(jsonString);

                JSONArray service = obj.getJSONArray("services");


                JSONObject serviceName = service.getJSONObject(i);

                String serviceNameString = serviceName.getString("title");

                JSONArray ServiceArray = serviceName.getJSONArray("elements");

                for(int j = 0; j <=ServiceArray.length();j++){
                    JSONObject ServiceArrayObject = ServiceArray.getJSONObject(j);
                    String section = ServiceArrayObject.getString("section");
                    String type = ServiceArrayObject.getString("type");
                    JSONArray tableauValue = ServiceArrayObject.getJSONArray("value");
                    String firstValue = tableauValue.getString(0);
                    boolean end = false;

                    if(j == ServiceArray.length()-1){
                        end = true;
                    }


                    addItem(createServicesItem(i+1,section,type,tableauValue,serviceNameString,end));
                }


            }catch (JSONException e){e.printStackTrace();}
        }
    }


    public static class ServicesItemInfos {
        public final String id;
        public final String section;
        public final String type;
        public final JSONArray firstValue;
        public final String serviceString;
        public final boolean end;

        public ServicesItemInfos(String id,String section,String type,JSONArray firstValue,String serviceString,boolean end) {
            this.id = id;
            this.section = section;
            this.type = type;
            this.firstValue = firstValue;
            this.serviceString = serviceString;
            this.end = end;
        }
    }
}




