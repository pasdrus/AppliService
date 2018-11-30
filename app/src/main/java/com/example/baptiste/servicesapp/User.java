package com.example.baptiste.servicesapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.drawable.Drawable;
import android.renderscript.Sampler;
import android.widget.EditText;


public class User {
    public static final List<UserItem> ITEMS = new ArrayList<UserItem>();
    public static final Map<String, UserItem> ITEM_MAP = new HashMap<String, UserItem>();


    private static void addItem(UserItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static UserItem createUsersItem(int position, String Name,String Service,String Value) {
        return new UserItem(String.valueOf(position),Name,Service,Value);
    }


    static {
        String jsonString = MainActivity.ReadFile("/pasdrus1.txt");
            try {

                    JSONObject obj = new JSONObject(jsonString);

                    JSONArray service = obj.getJSONArray("users");

                    for(int i = 0;i<service.length();i++){
                        JSONObject serviceName = service.getJSONObject(i);

                        String Service = serviceName.getString("Service");

                        JSONArray values = serviceName.getJSONArray("Values");

                        for(int j=0;j<values.length();j++){
                            JSONObject newObject = values.getJSONObject(j);

                            Iterator it = newObject.keys();
                            while(it.hasNext()){
                                String x = (String) it.next();
                                String value = (String) newObject.get(x);
                                addItem(createUsersItem(i+1,x, Service, value));

                            }
                        }
                    }
            }catch (Exception e){e.printStackTrace();}
        }



    public static class UserItem {
        public final String id;
        public final String name;
        public final String service;
        public final String value;

        public UserItem(String id,String name,String service,String value) {
            this.id = id;
            this.name = name;
            this.service = service;
            this.value = value;
        }
    }
}




