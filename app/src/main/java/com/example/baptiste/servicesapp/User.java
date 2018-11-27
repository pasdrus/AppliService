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

    private static UserItem createUsersItem(int position, String userName,String serviceString) {
        return new UserItem(String.valueOf(position),userName,serviceString);
    }


    static {
        // Add some sample items.
        String jsonString = MainActivity.loadJSONFromAsset("users.json");
        for (int i = 0; i <= 2; i++) {

            try {
                    String test1 = ((EditText) ItemInfosRecyclerViewAdapter.mapper.get("Netflix1")).getText().toString();
                    addItem(createUsersItem(i+1,test1,"Netflix"));
                }catch (Exception e){e.printStackTrace();}




            /*try{

                ////BORDEL POUR RENTRER DANS LE JSON :
                //// AU CAS OU REGARDER : http://www.vogella.com/tutorials/AndroidJSON/article.html
                ///// https://abhiandroid.com/programming/json
                ///// https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
                JSONObject obj = new JSONObject(jsonString);

                JSONArray service = obj.getJSONArray("users");



                JSONObject serviceName = service.getJSONObject(i);

                String serviceNameString = serviceName.getString("title");

                JSONArray ServiceArray = serviceName.getJSONArray("elements");

                for(int j = 0; j <=ServiceArray.length();j++){
                    JSONObject ServiceArrayObject = ServiceArray.getJSONObject(j);
                    String userName = ServiceArrayObject.getString("name");

                    addItem(createUsersItem(i+1,userName,serviceNameString));
                }


            }catch (JSONException e){e.printStackTrace();}**/

            /*

            try{

                ////BORDEL POUR RENTRER DANS LE JSON :
                //// AU CAS OU REGARDER : http://www.vogella.com/tutorials/AndroidJSON/article.html
                ///// https://abhiandroid.com/programming/json
                ///// https://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
                JSONObject obj = new JSONObject(jsonString);

                JSONArray service = obj.getJSONArray("users");



                JSONObject serviceName = service.getJSONObject(i);

                String serviceNameString = serviceName.getString("title");

                JSONArray ServiceArray = serviceName.getJSONArray("elements");

                for(int j = 0; j <=ServiceArray.length();j++){
                    JSONObject ServiceArrayObject = ServiceArray.getJSONObject(j);
                    String userName = ServiceArrayObject.getString("name");

                    addItem(createUsersItem(i+1,userName,serviceNameString));
                }


            }catch (JSONException e){e.printStackTrace();}
            */
        }
    }


    public static class UserItem {
        public final String id;
        public final String name;
        public final String serviceString;

        public UserItem(String id,String serviceString,String name) {
            this.id = id;
            this.serviceString = serviceString;
            this.name = name;
        }
    }
}




