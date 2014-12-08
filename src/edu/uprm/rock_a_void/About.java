package edu.uprm.rock_a_void;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


public class About extends Activity {
	
	TextView Title;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/rakoon.ttf");
        
        Title = (TextView) findViewById(R.id.GameTitle);
        Title.setTypeface(tf);
    }
}
