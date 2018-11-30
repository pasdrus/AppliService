package com.example.baptiste.servicesapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.drawable.Drawable;
import android.widget.EditText;


public class User {
    public static final List<UserItem> ITEMS = new ArrayList<UserItem>();
    public static final Map<String, UserItem> ITEM_MAP = new HashMap<String, UserItem>();


    private static void addItem(UserItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static UserItem createUsersItem(int position, String Name,String Service) {
        return new UserItem(String.valueOf(position),Name,Service);
    }


    static {
        // Add some sample items.

        String jsonString = MainActivity.ReadFile("/pasdrus.txt");



        for (int i = 0; i <= 30; i++) {

            try {
                    JSONObject obj = new JSONObject(jsonString);

                    JSONArray service = obj.getJSONArray("users");

                    JSONObject serviceName = service.getJSONObject(0);

                    String Name = serviceName.getString("Service");

                    JSONArray values = serviceName.getJSONArray("Values");

                    JSONObject jsonObject = values.getJSONObject(i);




                    addItem(createUsersItem(i+1,jsonObject.toString(),Name));

            }catch (Exception e){e.printStackTrace();}
        }
    }


    public static class UserItem {
        public final String id;
        public final String name;
        public final String service;

        public UserItem(String id,String name,String service) {
            this.id = id;
            this.name = name;
            this.service = service;
        }
    }
}




