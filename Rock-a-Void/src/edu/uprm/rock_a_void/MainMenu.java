package edu.uprm.rock_a_void;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainMenu extends Activity {

	TextView Start, Ranking, About;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/rakoon.ttf");
        
        Start = (TextView) findViewById(R.id.textViewStartGame);
        Start.setTypeface(tf);
        
        Ranking = (TextView) findViewById(R.id.TextViewRankings);
        Ranking.setTypeface(tf);
        
        About = (TextView) findViewById(R.id.TextViewAbout);
        About.setTypeface(tf);
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
