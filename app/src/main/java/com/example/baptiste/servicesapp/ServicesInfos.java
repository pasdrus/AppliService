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

    private static ServicesItemInfos createServicesItem(int position, String section,String type,String firstValue) {
        return new ServicesItemInfos(String.valueOf(position),"Section : " + section,"Type : " +type,"firstValue : " +firstValue);
    }

    static {
        // Add some sample items.
        for (int i = 0; i <= 1; i++) {
            String jsonString = MainActivity.loadJSONFromAsset();
            try{

                ////BORDEL POUR RENTRER DANS LE JSON :
                //// AU CAS OU REGARDER : http://www.vogella.com/tutorials/AndroidJSON/article.html
                ///// https://abhiandroid.com/programming/json
                ///// https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
                JSONObject obj = new JSONObject(jsonString);

                JSONArray service = obj.getJSONArray("services");

                JSONObject serviceName = service.getJSONObject(i);

                JSONArray ServiceArray = serviceName.getJSONArray("elements");

                for(int j = 0; j <=ServiceArray.length();j++){
                    JSONObject ServiceArrayObject = ServiceArray.getJSONObject(j);
                    String section = ServiceArrayObject.getString("section");
                    String type = ServiceArrayObject.getString("type");
                    JSONArray tableauValue = ServiceArrayObject.getJSONArray("value");
                    String firstValue = tableauValue.getString(0);

                    addItem(createServicesItem(i+1,section,type,firstValue));
                }


            }catch (JSONException e){e.printStackTrace();}
        }
    }


    public static class ServicesItemInfos {
        public final String id;
        public final String section;
        public final String type;
        public final String firstValue;

        public ServicesItemInfos(String id,String section,String type,String firstValue) {
            this.id = id;
            this.section = section;
            this.type = type;
            this.firstValue = firstValue;
        }
    }
}




