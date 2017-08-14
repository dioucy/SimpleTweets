package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweets.fragments.HomeTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.MentionsTimelineFragment;
import com.codepath.apps.mysimpletweets.fragments.TweetsListFragment;

public class TimelineActivity extends AppCompatActivity {
    private TweetsListFragment fragmentTweetsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the view pager
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        //set the view pager adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        //fing the pager sliding tabstrip
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //attach the pager tabstrip to the viewpager
        tabStrip.setViewPager(vpPager);

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
        return super.onOptionsItemSelected(item);
    }


    public void onCompose(MenuItem mi){
        //launch the profile view
        //Intent i = new Intent(this, ComposeDialogFragment.class);
        //startActivity(i);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        ComposeDialogFragment alertDialog = ComposeDialogFragment.newInstance("Some title");
        alertDialog.show(fm, "fragment_alert");


}


    public void onProfileView(MenuItem mi){
        //launch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    //return the oder of the fragments in the view pager
    public class TweetsPagerAdapter extends FragmentPagerAdapter{
        private String tabTitles [] = {"Home", "Mentions"};

        //Adapter gets the manager insert or remove fragment from activity
        public TweetsPagerAdapter(FragmentManager fm){
            super(fm);
        }

        //The oder and creation of fragments within the pager
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeTimelineFragment();
            }else if(position == 1){
                return new MentionsTimelineFragment();
            }else{
                return null;
            }
        }

        //Return the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        //how many fragment there are to swipe between
        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}
