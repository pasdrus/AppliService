package com.example.baptiste.servicesapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.drawable.Drawable;


public class ServicesContent {
    public static final List<ServicesItem> ITEMS = new ArrayList<ServicesItem>();
    public static final Map<String, ServicesItem> ITEM_MAP = new HashMap<String, ServicesItem>();


    private static void addItem(ServicesItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ServicesItem createServicesItem(int position, String service,String urlImage) {
        return new ServicesItem(String.valueOf(position),service,urlImage);
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

                JSONArray netflixServiceArray = serviceName.getJSONArray("elements");

                JSONObject netflixServiceArrayObject = netflixServiceArray.getJSONObject(0);

                JSONArray tableauUrlImages = netflixServiceArrayObject.getJSONArray("value");

                String urlString = tableauUrlImages.getString(0);

                addItem(createServicesItem(i+1,serviceNameString,urlString));
            }catch (JSONException e){e.printStackTrace();}
        }
    }


    public static class ServicesItem {
        public final String id;
        public final String title;
        public final String urlImage;

        public ServicesItem(String id,String title,String urlImage) {
            this.id = id;
            this.title = title;
            this.urlImage = urlImage;
        }
    }
}





