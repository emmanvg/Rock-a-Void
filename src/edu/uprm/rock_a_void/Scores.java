package edu.uprm.rock_a_void;

import java.util.ArrayList;
import java.util.Collections;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class Scores extends ListActivity
{
	private static final String SCORES = "scores";
	private SharedPreferences savedScores;		//User's best scores.
	private ArrayList<String> scores;			//List of saved scores.
	private ArrayAdapter<String> adapter;		//Binds scores to ListView.
	TextView Ranks;
    
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        
        savedScores = getSharedPreferences(SCORES, MODE_PRIVATE);
        
        //Store the saved tags in ArrayList and sort them
        scores = new ArrayList<String>(savedScores.getAll().keySet());
        Collections.sort(scores, String.CASE_INSENSITIVE_ORDER);
        
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
        	
        	adapter = new ArrayAdapter<String>(this, R.layout.list_item, scores);
            setListAdapter(adapter);
        }
        //Previous Activity is game
        //Received a new score
        else
        {   
        	 //Create ArrayAdapter and Bind it to ListView
            adapter = new ArrayAdapter<String>(this, R.layout.list_item, scores);
            setListAdapter(adapter);
            
        	String name = extras.getString("name");
        	int score = extras.getInt("score");
        	SharedPreferences.Editor preferencesEditor = savedScores.edit();
        	
        	preferencesEditor.putString(name, String.valueOf(score));
        	preferencesEditor.apply();
        	
        	//preferencesEditor.clear();
        	//preferencesEditor.commit();
        	
        	//If the tag is new, add it and sort all tags. Then display list
    		scores.add(String.valueOf(score));
    		Collections.sort(scores, String.CASE_INSENSITIVE_ORDER);
    		
    		adapter.notifyDataSetChanged();
    		finish();
        }
        
        //Register listener that opens browser when user touches a tag
        getListView().setOnItemClickListener(itemClickListener);
    }
    
    //ItemClickListener that launches web browser to display results
  	OnItemClickListener itemClickListener = new OnItemClickListener()
  	{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		{
			String tag = ((TextView) arg1).getText().toString();
			String name = savedScores.getString(tag, "");
			
			AlertDialog.Builder alert = new AlertDialog.Builder(Scores.this);
			alert.setTitle("The " + tag + "'s score:");
			alert.setMessage(name);

			alert.setPositiveButton("OK", new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{
    			}
			});

			alert.show();
		}
  	};
}
