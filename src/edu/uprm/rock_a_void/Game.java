package edu.uprm.rock_a_void;

import edu.uprm.rock_a_void.mObject;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Game extends Activity implements SensorEventListener{
	
	private static ArrayList<mObject> Rocks;
	private static mObject Ship;
	private static long delay;
	private static Random rand;
	private static TextView score, value;
	private static Display display;
	private static Point screen;
	private static ImageView ship, lRock, mRock, rRock;
	private static Sensor mAccelerometer;
	private static SensorManager mSensorManager;
	private static Integer rockavoid;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        rand = new Random();
        
        rockavoid = 0;
        delay = 0;
        
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/rakoon.ttf");
        
        value = (TextView) findViewById(R.id.scoreValue);
        score = (TextView) findViewById(R.id.score);
        
        score.setTypeface(tf);
        
        display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        screen = new Point();
        display.getRealSize(screen);
        
        ship = (ImageView) findViewById(R.id.imageSubmarine);
        
        mRock = (ImageView) findViewById(R.id.imageRockMiddle);
        lRock = (ImageView) findViewById(R.id.imageRockLeft);
        rRock = (ImageView) findViewById(R.id.imageRockRight);
        
        Rocks = new ArrayList<mObject>();
        
        Rocks.add(new mObject(lRock, 25.0));
        Rocks.add(new mObject(mRock, 25.0));
        Rocks.add(new mObject(rRock, 25.0));
        
        Ship = new mObject(ship, 10.0);
        
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }
    
    public void InitializeRocks(ArrayList<mObject> rocks)
    {
		// TODO Auto-generated method stub
    	int n = 0;
    	boolean [] actives = new boolean[3];
    	boolean all;
    	
    	for(mObject rock: rocks)
    	{
    		actives[n] = rock.getActive();
    		n++;
    	}
    	
    	all = ((!actives[0] && actives[1] && actives[2]) || (actives[0] && !actives[1] && actives[2]) || (actives[0] && actives[1] && !actives[2]));
    	
    	for(mObject rock: rocks)
    	{
    		Double chance = rand.nextDouble();
    		
    		if(chance > 0.9 && rock.getActive() == false && all == false)
    			rock.resetTranslationY(-150.0f);
    	}
	}

	@Override
    public void onSensorChanged(SensorEvent event)
    {
    	float translateShip = (( 1.5f + event.values[0]) * -1.0f) + ship.getTranslationX();
    	
    	for(mObject rock: Rocks)
    	{
	    		if(Ship.crash(rock) && rock.getActive())
	    		{
	    			AlertDialog.Builder alert = new AlertDialog.Builder(this);
	    			alert.setTitle("Game Over");
	    			alert.setMessage("Enter your name:");

	    			// Set an EditText view to get user input 
	    			final EditText input = new EditText(this);
	    			alert.setView(input);

	    			alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog, int whichButton) {
	    			  Editable value = input.getText();
	    			  // Do something with value!
	    			  
		    			Intent intent = new Intent(Game.this, Rankings.class);
		    			Bundle bundle = new Bundle();
		    			
		    			bundle.putInt("score", rockavoid);
		    			bundle.putBoolean("menu", false);
		    			bundle.putString("name", value.toString());
		    			
		    			intent.putExtras(bundle);
		    			startActivity(intent);
	    			  }
	    			});

	    			alert.show();
	    			// see http://androidsnippets.com/prompt-user-input-with-an-alertdialog	
	    			
	    		}
    	}
    	
    	if(translateShip < (screen.x / 2.0f) - 145 && translateShip > ((-1.0f * screen.x) / 2.0f) + 145)
    		Ship.horizontalTranslation(translateShip);
    	
    	for(mObject rock: Rocks)
    	{
    		if(rock.getActive())
    		{
    			float translateRock = rock.getTranslationY() + 4.0f;
    			
    			if(translateRock < screen.y)
    				rock.setTranslationY(translateRock);
    				
    			else
		    	{
		    		rockavoid++;
		    		value.setText(" " + rockavoid.toString());
		    		rock.setTranslationY(-150.0f);
		    		rock.deActivate();
		    	}
    		}
    	}
    	
    	if(delay == 7)
    	{
    		InitializeRocks(Rocks);
    		delay = 0;
    	}
    	
    	delay++;
    }


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
