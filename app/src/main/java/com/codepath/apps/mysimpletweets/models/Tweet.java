package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jerry on 8/3/2017.
 */

//Start the json and store the data
public class Tweet {
    //listing of attributes
    private String body;

    public String getBody() {
        return body;
    }
    private long uid;
    public long getUid() {
        return uid;
    }
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }


    private User user;

    public User getUser() {
        return user;
    }




    //Deserialize the json and build tweet oject
    //Tweet.fromJSON
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();

        //Extract and store the values from the json
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Return the tweet object
        return tweet;

    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){

        ArrayList<Tweet> tweets = new ArrayList<>();
        //Iterate the jsonarray and create tweets
        for (int i = 0; i < jsonArray.length(); i++){

            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if(tweet != null){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;
    }

}
