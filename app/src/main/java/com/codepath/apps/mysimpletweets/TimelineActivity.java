package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Find the listview
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        //Create the arraylist(data source)
        tweets = new ArrayList<>();
        //Construct the adapter from data source
        aTweets = new TweetsArrayAdapter(this, tweets);
        //Connect adapter to listview
        lvTweets.setAdapter(aTweets);
        //Get the client
        client = TwitterApplication.getRestClient(); //only one client
        populateTimeline();

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();

        if (id == R.id.action_settings) {
           /* Intent intent = new Intent(TimelineActivity.this, ComposeDialogFragment.class);
            startActivity(intent);
            return true;*/
            FragmentManager fm = getSupportFragmentManager();
            ComposeDialogFragment editNameDialogFragment = ComposeDialogFragment.newInstance("Your Tweet");
            editNameDialogFragment.show(fm, "fragment_edit_name");

        }
        return super.onOptionsItemSelected(item);
    }

    //Send an API request to get the timeline json
    //Fill the listview with the tweet object from the json
    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler(){

        //Success
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                //super.onSuccess(statusCode, headers, response);
                aTweets.addAll(Tweet.fromJSONArray(json));
            }

            //Failure


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                //Deserialize JSON
                //create models and add them to the adapter
                //Load the model data into the ListView

            }
        });



    }



}
