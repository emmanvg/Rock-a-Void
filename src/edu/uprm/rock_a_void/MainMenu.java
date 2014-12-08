package edu.uprm.rock_a_void;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends Activity{

	TextView Title;
	Button Start, Ranking, About;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/rakoon.ttf");
        
        Title = (TextView) findViewById(R.id.titleText);
        Title.setTypeface(tf);
        
        Start = (Button) findViewById(R.id.buttonStartGame);
        Start.setTypeface(tf);
        Start.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent i = new Intent(getApplicationContext(), Game.class);
            	startActivity(i);
        	}
        });
               
        Ranking = (Button) findViewById(R.id.buttonRankings);
        Ranking.setTypeface(tf);
        Ranking.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent i = new Intent(getApplicationContext(), Rankings.class);
        		Bundle bundle = new Bundle();
        		
        		bundle.putBoolean("menu", true);
        		i.putExtras(bundle);
            	startActivity(i);
        	}
        });
        
        About = (Button) findViewById(R.id.buttonAbout);
        About.setTypeface(tf);
        About.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent i = new Intent(getApplicationContext(), About.class);
            	startActivity(i);
        	}
        });
    }
}
