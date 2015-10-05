package com.cabatuan.menuactivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cobalt on 9/3/15.
 */
public class MainActivity extends IntroActivity implements ExpandableListView.OnChildClickListener {

    /// EXPANDABLE LIST VIEW
    private DrawerLayout drawer;
    private ExpandableListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ArrayList<String> groupItem;
    private HashMap<String, List<String>> childItem;
    public final static String DEBUG_TAG = "MainActivity";
    private TextToSpeech tts;              // Text-to-Speech engine
    private boolean ttsLoaded = false;   // true when TTS engine is loaded


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// Initialize layout and actionbar/title bar on top
        centerActionBarTitle(); // centers the title bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        setContentView(R.layout.activity_main);

        /// Expandable list view
        setGroupData();
        setChildGroupData();

        initializeTTS();
        initDrawer();
    }

    private void initializeTTS() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ttsLoaded = true;
            }
        });
    }


    private void initDrawer() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new MyExpandableListAdapter(this, groupItem, childItem));

        drawerList.setOnChildClickListener(this);

        drawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, "Clicked On " + groupPosition, Toast.LENGTH_SHORT).show();
                talk("Clicked On Option " + groupPosition);
            }
        });

        // actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer,
        // R.drawable.ic_drawer, R.string.open_drawer,
        // R.string.close_drawer) {
        // public void onDrawerClosed(View view) {
        // getActionBar().setSubtitle("open");
        // }
        //
        // /** Called when a drawer has settled in a completely open state. */
        // public void onDrawerOpened(View drawerView) {
        // getActionBar().setSubtitle("close");
        // }
        //
        // };
        //
        // drawer.setDrawerListener(actionBarDrawerToggle);

    }

    private void talk(String message){
        if (ttsLoaded) {
            tts.setSpeechRate(0.6f);
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void setGroupData() {

        groupItem = new ArrayList<String>();
        groupItem.add("Option 0");
        groupItem.add("Option 1");
        groupItem.add("Option 2");
        groupItem.add("Extras");
    }



    public void setChildGroupData() {

        childItem = new HashMap<String, List<String>>();
        /**
         * Add Data For Header 0
         */
        ArrayList<String> child = new ArrayList<String>();
        child.add("Child 0-0");
        child.add("Child 0-1");
        childItem.put(groupItem.get(0), child);

        /**
         * Add Data For Header 1
         */
        child = new ArrayList<String>();
        child.add("child 1-0");
        childItem.put(groupItem.get(1), child);
        /**
         * Add Data For Header 2
         */
        child = new ArrayList<String>();
        child.add("child 2-0");
        child.add("child 2-1");
        child.add("child 2-2");
        child.add("child 2-3");
        childItem.put(groupItem.get(2), child);
        /**
         * Add Data For Header 3
         */
        child = new ArrayList<String>();
        child.add("Contact Us");
        child.add("About Us");
        childItem.put(groupItem.get(3), child);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "Clicked On Child " + groupPosition + "-" + childPosition,
                Toast.LENGTH_SHORT).show();
        talk("Clicked On Child " + groupPosition + "-" + childPosition);
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, BackgroundSound.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, BackgroundSound.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, BackgroundSound.class));
    }
}