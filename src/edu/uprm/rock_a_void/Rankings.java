package edu.uprm.rock_a_void;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Rankings extends Activity
{
	TextView Ranks;
    
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/rakoon.ttf");
        
        Ranks = (TextView) findViewById(R.id.title);
        Ranks.setTypeface(tf);
        
        //receive the Bundle and verify if the previous activity was main or game
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        
        boolean fromActivityMain = extras.getBoolean("menu");
        
        if(fromActivityMain == true)
        {
        	System.out.println("Call From Main");
        }
        //Previous Activity is game
        //Received a new score
        else
        {
        	String name = extras.getString("name");
        	int score = extras.getInt("score");
        	System.out.println("Call from Game " + score + " " + name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
