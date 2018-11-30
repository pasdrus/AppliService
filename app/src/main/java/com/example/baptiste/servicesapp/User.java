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

    private static UserSubItem createUsersSubItem(int position, String Name,String Service,String Value) {
        return new UserSubItem(String.valueOf(position),Name,Service,Value);
    }

    private static UserItem createUsersItem(int position, ArrayList e) {
        return new UserItem(String.valueOf(position),e);
    }


    static {
        String jsonString = MainActivity.ReadFile("/Utilisateurs.txt");
        try {

            JSONObject obj = new JSONObject(jsonString);

            JSONArray service = obj.getJSONArray("users");

            for(int i = 0;i<service.length();i++){
                JSONObject serviceName = service.getJSONObject(i);
                String Service = serviceName.getString("Service");


                JSONArray values = serviceName.getJSONArray("Values");

                for(int j=0;j<values.length();j++){
                    JSONObject newObject = values.getJSONObject(j);
                    ArrayList<UserSubItem> usi = new ArrayList<>();
                    Iterator it = newObject.keys();
                    while(it.hasNext()){
                        String x = (String) it.next();
                        String value = (String) newObject.get(x);
                        usi.add(createUsersSubItem(i+1,x, Service, value));
                        addItem(createUsersItem(j,usi));
                    }
                }

            }
        }catch (Exception e){e.printStackTrace();}
    }



    public static class UserItem {
        public final String id;
        public final ArrayList<UserSubItem> list;

        public UserItem(String id, ArrayList l) {
            this.id = id;
            this.list = l;
        }
    }
    public static class UserSubItem {
        public final String id;
        public final String name;
        public final String service;
        public final String value;

        public UserSubItem(String id,String name,String service,String value) {
            this.id = id;
            this.name = name;
            this.service = service;
            this.value = value;
        }
    }
}




