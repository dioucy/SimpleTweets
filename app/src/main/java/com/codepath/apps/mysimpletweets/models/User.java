package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jerry on 8/3/2017.
 */

public class User {
    //list the attributes
    private String name;
    private long uid;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    private String screenName;
    private String profileImageUrl;

    //deserialize user json to User
    public static User fromJSON(JSONObject json){
        User u = new User();
     //Extract and fill values
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }


     //Return a user
        return u;
    }

}
